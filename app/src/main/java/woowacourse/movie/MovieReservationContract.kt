package woowacourse.movie

interface MovieReservationContract {
    interface View {
        fun showCurrentResultTicketCountView()
    }

    interface Presenter {
        fun clickMinusNumberButton()

        fun clickPlusNumberButton()
    }
}

class MovieReservationModel {
    var ticketCount = 1
        private set

    fun minusTicketCount() {
        if (ticketCount > 1) {
            ticketCount--
        }
    }

    fun plusTicketCount() {
        ticketCount++
    }
}

class MovieReservationPresenter(
    private val view: MovieReservationContract.View,
) : MovieReservationContract.Presenter {
    private val model: MovieReservationModel = MovieReservationModel()

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
