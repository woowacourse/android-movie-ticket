package woowacourse.movie.presentation.reservation.result

import android.content.Intent
import woowacourse.movie.data.MovieDao
import woowacourse.movie.model.Seats
import woowacourse.movie.model.Ticket
import woowacourse.movie.presentation.reservation.seat.ReservationSeatActivity.Companion.SEATS
import woowacourse.movie.presentation.screen.detail.MovieDetailActivity.Companion.DEFAULT_MOVIE_ID
import woowacourse.movie.presentation.screen.movie.ScreeningMovieActivity.Companion.MOVIE_ID

class ReservationResultPresenter(
    private val view: ReservationResultContract.View,
    private val dao: MovieDao,
) :
    ReservationResultContract.Presenter {
    override fun fetch(intent: Intent) {
        val ticket = intent.getSerializableExtra("ticket") as? Ticket ?: Ticket()
        val seats = intent.getSerializableExtra(SEATS) as? Seats ?: Seats()
        val movieId = intent.getIntExtra(MOVIE_ID, DEFAULT_MOVIE_ID)
        val movie = dao.find(movieId)
        movie.let {
            view.showMovieInformation(
                movie.title,
            )
        }
        view.showReservationInformation(ticket, seats)
    }
}
