package woowacourse.movie.presenter

import android.util.Log
import woowacourse.movie.contract.MovieReservationContract
import woowacourse.movie.model.MovieReservationData

class MovieReservationPresenter(
    private val view: MovieReservationContract.View,
) : MovieReservationContract.Presenter {
    private val model: MovieReservationData = MovieReservationData()

    private val ticketCount
        get() = model.ticketCount

    override fun setCurrentResultTicketCountInfo() {
        view.showCurrentResultTicketCountView(ticketCount.number)
    }

    override fun setMovieInfo() {
        view.setMovieView()
    }

    override fun setPlusButtonClickInfo() {
        model.plusTicketCount()
        view.showCurrentResultTicketCountView(ticketCount.number)
    }

    override fun setMinusButtonClickInfo() {
        runCatching { model.minusTicketCount() }
            .onFailure { Log.d("error", "setClickListener: ${it.message}") }
        view.showCurrentResultTicketCountView(ticketCount.number)
    }

    override fun setTicketingButtonClickInfo() {
        view.startMovieTicketActivity(ticketCount.number)
    }
}
