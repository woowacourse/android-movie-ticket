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

class MovieReservationModel() {
    private var ticketCount = 1

    fun minusNumber() {
        if (ticketCount > 1) {
            ticketCount--
        }
    }

    fun plusNumber() {
        ticketCount++
    }

    fun getTicketCount(): Int {
        return ticketCount
    }
}

class MovieReservationPresenter(
    private var view: MovieReservationContract.View,
) : MovieReservationContract.Presenter {
    private var model: MovieReservationModel = MovieReservationModel()

    fun getTicketCount(): Int = model.getTicketCount()

    override fun clickMinusNumberButton() {
        model.minusNumber()
        view.showCurrentResultTicketCountView()
    }

    override fun clickPlusNumberButton() {
        model.plusNumber()
        view.showCurrentResultTicketCountView()
    }
}
