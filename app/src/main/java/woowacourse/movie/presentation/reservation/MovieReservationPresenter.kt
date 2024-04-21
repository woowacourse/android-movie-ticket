package woowacourse.movie.presentation.reservation

import woowacourse.movie.domain.model.TicketCounter

class MovieReservationPresenter(
    private val view: MovieReservationContract.View,
) : MovieReservationContract.Presenter {
    private val model: TicketCounter = TicketCounter()

    val ticketCount
        get() = model.ticketCount

    override fun clickMinusNumberButton() {
        model.minusTicketCount()
        view.showCurrentResultTicketCountView()
    }

    override fun clickPlusNumberButton() {
        model.plusTicketCount()
        view.showCurrentResultTicketCountView()
    }
}
