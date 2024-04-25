package woowacourse.movie.ticket.presenter

import woowacourse.movie.reservation.model.Count
import woowacourse.movie.ticket.contract.MovieTicketContract
import woowacourse.movie.ticket.model.TicketDataResource

class MovieTicketPresenter(
    val view: MovieTicketContract.View,
) : MovieTicketContract.Presenter {
    private val ticketCount
        get() = TicketDataResource.ticketCount

    override fun storeTicketCount(count: Count) {
        TicketDataResource.ticketCount = count
    }

    override fun setTicketInfo() {
        view.showTicketView(TicketDataResource.ticket[0], ticketCount)
    }

    override fun storeScreeningDate(date: String) {
        TicketDataResource.screeningDate = date
    }

    override fun storeScreeningTime(time: String) {
        TicketDataResource.screeningTime = time
    }

    override fun setScreeningDateInfo() {
        view.showScreeningDate(TicketDataResource.screeningDate)
    }

    override fun setScreeningTimeInfo() {
        view.showScreeningTime(TicketDataResource.screeningTime)
    }
}
