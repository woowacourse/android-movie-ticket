package woowacourse.movie.presenter

import woowacourse.movie.model.Count
import woowacourse.movie.model.MovieData.findScreeningDataById
import woowacourse.movie.model.Result
import woowacourse.movie.model.screening.AvailableTimes
import woowacourse.movie.model.ticketing.BookingDateTime
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
        selectedDate: kotlin.String?,
        selectedTime: kotlin.String?,
    ) {
        when (val screening = findScreeningDataById(screeningId)) {
            is Result.Success -> {
                val initialDate =
                    selectedDate?.let { LocalDate.parse(it) } ?: screening.data.dates.first()
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
                                selectedTime?.let { LocalTime.parse(it) }
                                    ?: availableTimes.localTimes.first(),
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
            ticketingForm.numberOfTickets.decrease()
            ticketingContractView.updateCount(ticketingForm.numberOfTickets.currentValue)
        }.onFailure {
            ticketingContractView.showToastMessage(ErrorMessage.ERROR_NON_POSITIVE_NUMBER.value)
        }
    }

    override fun increaseCount() {
        ticketingForm.numberOfTickets.increase()
        ticketingContractView.updateCount(ticketingForm.numberOfTickets.currentValue)
    }

    override fun reserveTickets() {
        ticketingContractView.navigateToSeatSelection(ticketingForm)
    }

    override fun updateDate(date: kotlin.String) {
        ticketingForm =
            ticketingForm.copy(
                bookingDateTime =
                    BookingDateTime(LocalDate.parse(date), ticketingForm.bookingDateTime.time),
            )
        ticketingUiState =
            ticketingUiState.copy(availableTimes = AvailableTimes.of(LocalDate.parse(date)))
        ticketingContractView.updateAvailableTimes(ticketingUiState.availableTimes.localTimes)
    }

    override fun updateTime(time: kotlin.String) {
        ticketingForm =
            ticketingForm.copy(
                bookingDateTime =
                    BookingDateTime(ticketingForm.bookingDateTime.date, LocalTime.parse(time)),
            )
    }
}
