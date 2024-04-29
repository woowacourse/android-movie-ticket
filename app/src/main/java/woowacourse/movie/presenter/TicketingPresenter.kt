package woowacourse.movie.presenter

import woowacourse.movie.model.Count
import woowacourse.movie.model.MovieData.findScreeningDataById
import woowacourse.movie.model.Result
import woowacourse.movie.model.screening.AvailableTimes
import woowacourse.movie.model.ticketing.BookingDateTime
import woowacourse.movie.presenter.contract.SelectedDate
import woowacourse.movie.presenter.contract.SelectedTime
import woowacourse.movie.presenter.contract.TicketingContract
import woowacourse.movie.view.state.TicketingForm
import woowacourse.movie.view.state.TicketingUiState
import woowacourse.movie.view.utils.ErrorMessage
import java.time.LocalDate
import java.time.LocalTime

class TicketingPresenter(
    private val ticketingContractView: TicketingContract.View,
) : TicketingContract.Presenter {
    lateinit var ticketingForm: TicketingForm
        private set
    lateinit var ticketingUiState: TicketingUiState
        private set

    override fun initializeTicketingData(
        screeningId: Long,
        initialCount: Int,
        selectedDate: SelectedDate?,
        selectedTime: SelectedTime?,
    ) {
        when (val screening = findScreeningDataById(screeningId)) {
            is Result.Success -> {
                val initialDate = selectedDate.toLocalDate(screening.data.dates)
                val availableTimes = AvailableTimes.of(initialDate)
                ticketingUiState =
                    TicketingUiState(
                        screening = screening.data,
                        availableTimes = availableTimes,
                    )

                ticketingForm =
                    TicketingForm(
                        screeningId = screeningId,
                        movieTitle = screening.data.movie.title,
                        numberOfTickets = Count(initialCount),
                        bookingDateTime =
                            BookingDateTime(
                                initialDate,
                                selectedTime.toLocalTime(availableTimes.localTimes),
                            ),
                    )
                ticketingContractView.assignInitialView(
                    screening.data,
                    initialCount,
                    ticketingForm.bookingDateTime.date,
                    ticketingForm.bookingDateTime.time,
                )
            }

            is Result.Error -> ticketingContractView.showToastMessage(ErrorMessage.ERROR_INVALID_SCREENING_ID.value)
        }
    }

    override fun decreaseCount() {
        runCatching {
            ticketingContractView.updateCount(ticketingForm.numberOfTickets.decrease())
        }.onFailure {
            ticketingContractView.showToastMessage(ErrorMessage.ERROR_NON_POSITIVE_NUMBER.value)
        }
    }

    override fun increaseCount() {
        ticketingContractView.updateCount(ticketingForm.numberOfTickets.increase())
    }

    override fun reserveTickets() {
        ticketingContractView.navigateToSeatSelection(ticketingForm)
    }

    override fun updateDate(date: String) {
        ticketingForm =
            ticketingForm.copy(
                bookingDateTime =
                    BookingDateTime(LocalDate.parse(date), ticketingForm.bookingDateTime.time),
            )
        ticketingUiState =
            ticketingUiState.copy(availableTimes = AvailableTimes.of(LocalDate.parse(date)))
        ticketingContractView.updateAvailableTimes(ticketingUiState.availableTimes.localTimes)
    }

    override fun updateTime(time: String) {
        ticketingForm =
            ticketingForm.copy(
                bookingDateTime =
                    BookingDateTime(ticketingForm.bookingDateTime.date, LocalTime.parse(time)),
            )
    }

    private fun SelectedDate?.toLocalDate(availableDates: List<LocalDate>): LocalDate =
        this?.let { LocalDate.parse(it) } ?: availableDates.first()

    private fun SelectedDate?.toLocalTime(availableTimes: List<LocalTime>): LocalTime =
        this?.let { LocalTime.parse(this) } ?: availableTimes.first()
}
