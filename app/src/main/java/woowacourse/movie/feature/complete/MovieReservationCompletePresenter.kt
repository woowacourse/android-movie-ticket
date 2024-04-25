package woowacourse.movie.feature.complete

import woowacourse.movie.model.ReservationCount
import woowacourse.movie.model.Ticket
import woowacourse.movie.model.data.MovieRepository
import java.time.LocalDateTime

class MovieReservationCompletePresenter(
    private val view: MovieReservationCompleteContract.View,
    private val movieRepository: MovieRepository,
) : MovieReservationCompleteContract.Presenter {
    override fun loadMovieData(
        movieId: Long,
        reservationTime: LocalDateTime,
        reservationCountValue: Int,
    ) {
        val movie = movieRepository.find(movieId)
        val reservationCount =
            runCatching {
                ReservationCount(reservationCountValue)
            }.getOrElse {
                view.handleError(it)
                return
            }
        val ticket = Ticket(reservationTime, reservationCount)
        view.setUpReservationCompleteView(movie, ticket)
    }
}
