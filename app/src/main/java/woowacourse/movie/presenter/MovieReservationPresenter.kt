package woowacourse.movie.presenter

import woowacourse.movie.contract.MovieReservationContract
import woowacourse.movie.model.MovieReservationData

class MovieReservationPresenter(
    private val view: MovieReservationContract.View,
) : MovieReservationContract.Presenter {
    private val model: MovieReservationData = MovieReservationData()

    val ticketCount
        get() = model.ticketCount

    override fun clickMinusNumberButton() {
        model.minusTicketCount()
        setCurrentResultTicketCountInfo()
    }

    override fun clickPlusNumberButton() {
        model.plusTicketCount()
        setCurrentResultTicketCountInfo()
    }

    override fun setMovieInfo() {
        view.setMovieView()
    }

    override fun setCurrentResultTicketCountInfo() {
        view.showCurrentResultTicketCountView(ticketCount)
    }

    override fun setClickListener() {
        view.setOnButtonsClickListener()
    }
}
