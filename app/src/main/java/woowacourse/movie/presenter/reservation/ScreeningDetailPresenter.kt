package woowacourse.movie.presenter.reservation

import woowacourse.movie.contract.reservation.ScreeningDetailContract
import woowacourse.movie.domain.reservation.Screening
import java.time.LocalDate

class ScreeningDetailPresenter(
    private val view: ScreeningDetailContract.View,
    private val screening: Screening,
    ticketCount: Int? = null,
    timeItemPosition: Int? = null,
) : ScreeningDetailContract.Presenter {
    override var ticketCount = ticketCount ?: DEFAULT_TICKET_COUNT
    override var timeItemPosition = timeItemPosition ?: DEFAULT_TIME_ITEM_POSITION

    override fun fetchScreeningDetail() {
        view.setScreeningDetail(screening)
        view.setTicketCount(ticketCount)
    }

    override fun fetchAvailableTimes(date: LocalDate) {
        view.setAvailableTimes(screening.showtimes(date), timeItemPosition)
    }

    override fun plusTicketCount() {
        ticketCount++
        view.setTicketCount(ticketCount)
    }

    override fun minusTicketCount() {
        ticketCount = ticketCount.minus(1).coerceAtLeast(1)
        view.setTicketCount(ticketCount)
    }

    override fun fetchAvailableSeats() {
        view.navigateToSeatSelectionScreen(screening.title, ticketCount)
    }

    companion object {
        private const val DEFAULT_TICKET_COUNT = 1
        private const val DEFAULT_TIME_ITEM_POSITION = 0
    }
}
