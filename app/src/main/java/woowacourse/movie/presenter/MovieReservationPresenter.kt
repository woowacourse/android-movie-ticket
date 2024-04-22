package woowacourse.movie.presenter

import android.content.Context
import android.widget.Toast
import woowacourse.movie.contract.MovieReservationContract
import woowacourse.movie.model.MovieReservationData

class MovieReservationPresenter(
    private val view: MovieReservationContract.View,
) : MovieReservationContract.Presenter {
    private val model: MovieReservationData = MovieReservationData()
    private var toast: Toast? = null

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
        runCatching {
            model.minusTicketCount()
            view.showCurrentResultTicketCountView(ticketCount.number)
        }.onFailure {
            toast?.cancel()
            toast = Toast.makeText(view as Context, it.message, Toast.LENGTH_SHORT)
            toast?.show()
        }
    }

    override fun setTicketingButtonClickInfo() {
        view.startMovieTicketActivity(ticketCount.number)
    }
}
