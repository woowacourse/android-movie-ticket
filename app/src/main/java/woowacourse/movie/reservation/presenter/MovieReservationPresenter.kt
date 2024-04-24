package woowacourse.movie.reservation.presenter

import woowacourse.movie.list.model.Movie
import woowacourse.movie.list.model.MovieDataSource
import woowacourse.movie.reservation.contract.MovieReservationContract
import woowacourse.movie.reservation.model.MovieReservationMovieData
import woowacourse.movie.reservation.model.MovieReservationTicketCountData

class MovieReservationPresenter(
    private val view: MovieReservationContract.View,
) : MovieReservationContract.Presenter {
    private val model: MovieReservationTicketCountData = MovieReservationTicketCountData()

    private val ticketCount
        get() = model.ticketCount

    override fun setCurrentResultTicketCountInfo() {
        view.showCurrentResultTicketCountView(ticketCount.number)
    }

    override fun storeMovieData(movieData: Movie) {
        MovieReservationMovieData.movieData = movieData
    }

    override fun setMovieInfo() {
        val movie = MovieReservationMovieData.movieData ?: MovieDataSource.emptyMovie
        view.setMovieView(movie)
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
            view.showToast(it.message ?: "")
        }
    }

    override fun setTicketingButtonClickInfo() {
        view.startMovieTicketActivity(ticketCount.number)
    }
}
