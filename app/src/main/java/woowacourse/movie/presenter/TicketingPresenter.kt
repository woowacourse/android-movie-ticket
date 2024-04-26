package woowacourse.movie.presenter

import woowacourse.movie.model.Count
import woowacourse.movie.model.MovieData.findScreeningDataById
import woowacourse.movie.model.Result
import woowacourse.movie.model.screening.AvailableTimes
import woowacourse.movie.model.ticketing.BookingDateTime
import woowacourse.movie.presenter.contract.TicketingContract
import java.time.LocalDate
import java.time.LocalTime

class TicketingPresenter(
    private val ticketingContractView: TicketingContract.View,
    screeningId: Long,
    initialCount: Int,
) : TicketingContract.Presenter {
    lateinit var availableTimes: AvailableTimes
        private set
    private val screening = findScreeningDataById(screeningId)
    private val count = Count(initialCount)
    private lateinit var date: LocalDate
    private lateinit var time: LocalTime

    val countValue: Int
        get() = count.value

    override fun initializeTicketingData() {
        when (screening) {
            is Result.Success -> {
                date = screening.data.dates.first()
                availableTimes = AvailableTimes.of(date)
                time = availableTimes.localTimes.first()
                ticketingContractView.assignInitialView(screening.data, count.value)
            }

            is Result.Error -> {
                ticketingContractView.showToastMessage(screening.message)
            }
        }
    }

    override fun decreaseCount() {
        runCatching {
            count.decrease()
            ticketingContractView.updateCount(count.value)
        }.onFailure {
            it.message?.let { message ->
                ticketingContractView.showToastMessage(message)
            }
        }
    }

    override fun increaseCount() {
        count.increase()
        ticketingContractView.updateCount(count.value)
    }

    override fun reserveTickets() {
        when (screening) {
            is Result.Success -> {
                ticketingContractView.navigateToSeatSelection(
                    screeningId = screening.data.screeningId,
                    count = count.value,
                    bookingDateTime = BookingDateTime(date, time),
                )
            }

            is Result.Error -> {
                ticketingContractView.showToastMessage(screening.message)
            }
        }
    }

    override fun updateDate(date: String) {
        this.date = LocalDate.parse(date)
    }

    override fun updateTime(time: String) {
        this.time = LocalTime.parse(time)
    }
}
