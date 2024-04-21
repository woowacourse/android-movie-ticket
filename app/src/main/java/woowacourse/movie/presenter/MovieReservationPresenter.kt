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
        view.showCurrentResultTicketCountView(ticketCount)
    }

    override fun setMovieInfo() {
        view.setMovieView()
    }

    override fun setPlusButtonClickInfo() {
        model.plusTicketCount()
        view.showCurrentResultTicketCountView(ticketCount)
    }

    override fun setMinusButtonClickInfo() {
        runCatching { model.minusTicketCount() }
            .onFailure { Log.d("error", "setClickListener: ${it.message}") }
        view.showCurrentResultTicketCountView(ticketCount)
    }

    override fun setTicketingButtonClickInfo()  {
        view.startMovieTicketActivity(ticketCount)
    }
}
