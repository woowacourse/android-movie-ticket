package woowacourse.movie.feature.reservation

import woowacourse.movie.model.ReservationCount
import woowacourse.movie.model.data.MovieRepository

class MovieReservationPresenter(
    private val view: MovieReservationContract.View,
    private val movieRepository: MovieRepository,
) : MovieReservationContract.Presenter {
    private lateinit var reservationCount: ReservationCount

    override fun setUpReservationCount() {
        reservationCount = ReservationCount()
        view.updateReservationCount(reservationCount.count)
    }

    override fun loadMovieData(movieId: Long) {
        val movie = movieRepository.find(movieId)
        view.setUpReservationView(movie)
    }

    override fun decreaseReservationCount() {
        reservationCount--
        view.updateReservationCount(reservationCount.count)
    }

    override fun increaseReservationCount() {
        reservationCount++
        view.updateReservationCount(reservationCount.count)
    }

    override fun reserveMovie() {
        view.moveReservationCompleteView(reservationCount.count)
    }

    override fun updateReservationCount(reservationCountValue: Int) {
        reservationCount =
            runCatching {
                ReservationCount(reservationCountValue)
            }.getOrElse {
                view.handleError(it)
                return
            }
        view.updateReservationCount(reservationCount.count)
    }
}
