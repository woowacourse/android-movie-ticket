package woowacourse.movie.presenter.reservation

import woowacourse.movie.contract.reservation.ReservationContract
import woowacourse.movie.domain.reservation.Screening
import java.time.LocalDate

class ReservationPresenter(
    private val view: ReservationContract.View,
    private val screening: Screening,
    ticketCount: Int? = null,
    timeItemPosition: Int? = null,
) : ReservationContract.Presenter {
    private var ticketCount = ticketCount ?: DEFAULT_TICKET_COUNT
    private var timeItemPosition = timeItemPosition ?: DEFAULT_TIME_ITEM_POSITION

    override fun presentPoster() {
        view.setPoster(screening.id)
    }

    override fun presentTitle() {
        view.setTitle(screening.title)
    }

    override fun presentPeriod() {
        screening.run {
            view.setPeriod(
                startYear,
                startMonth,
                startDay,
                endYear,
                endMonth,
                endDay,
            )
        }
    }

    override fun presentRunningTime() {
        view.setRunningTime(screening.runningTime)
    }

    override fun presentDates() {
        view.setDates(screening.availableDates())
    }

    override fun presentTimes(date: LocalDate) {
        view.setTimes(screening.showtimes(date), timeItemPosition)
    }

    override fun presentTicketCount() {
        view.setTicketCount(ticketCount)
    }

    override fun plusTicketCount() {
        ticketCount++
        presentTicketCount()
    }

    override fun minusTicketCount() {
        ticketCount = ticketCount.minus(1).coerceAtLeast(1)
        presentTicketCount()
    }

    override fun presentAvailableSeats() {
        view.navigateToSeatSelectionScreen(screening.title, ticketCount)
    }

    override fun getTicketCount(): Int = ticketCount

    override fun getItemPosition(): Int = timeItemPosition

    override fun setTimeItemPosition(position: Int) {
        timeItemPosition = position
    }

    companion object {
        private const val DEFAULT_TICKET_COUNT = 1
        private const val DEFAULT_TIME_ITEM_POSITION = 0
    }
}
