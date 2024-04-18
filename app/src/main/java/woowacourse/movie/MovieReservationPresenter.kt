package woowacourse.movie

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
