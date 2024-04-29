package woowacourse.movie.presentation.reservation.seat

import android.content.Intent
import woowacourse.movie.data.MovieDao
import woowacourse.movie.model.Movie
import woowacourse.movie.model.Seat
import woowacourse.movie.model.Seats
import woowacourse.movie.model.Ticket
import woowacourse.movie.presentation.screen.detail.MovieDetailActivity.Companion.DEFAULT_MOVIE_ID
import woowacourse.movie.presentation.screen.detail.MovieDetailActivity.Companion.TICKET
import woowacourse.movie.presentation.screen.movie.ScreeningMovieActivity.Companion.MOVIE_ID
import woowacourse.movie.util.intentSerializable

class ReservationSeatPresenter(
    private val view: ReservationSeatContract.View,
    private val dao: MovieDao,
) : ReservationSeatContract.Presenter {
    private var ticket: Ticket = Ticket()
    private lateinit var seat: Seat
    private lateinit var movie: Movie
    val seats: Seats by lazy { Seats() }

    override fun fetch(intent: Intent) {
        val movieId = intent.getIntExtra(MOVIE_ID, DEFAULT_MOVIE_ID)
        movie = dao.find(movieId)
        ticket =
            intent.intentSerializable(TICKET, Ticket::class.java) ?: error("ticket 정보가 없습니다.")
        view.setUpView(movie.title)
    }

    override fun onClickedSeat(
        row: Int,
        col: Int,
    ) {
        seat = Seat(row, col)
        when (seat in seats) {
            true -> {
                seats.removeSeat(seat)
                view.updateSeatColorToWhite(row, col)
            }

            false -> {
                seats.addSeat(seat)
                view.updateSeatColorToYellow(row, col)
            }
        }
        view.showSeatPrice(seats.totalPrice())
    }

    override fun checkSeatsCount() {
        when (seats.seats.size == ticket.count) {
            true -> view.ableClickCompleteText()
            false -> view.unableClickCompleteText()
        }
    }

    override fun loadReservationInfo() {
        view.navigateToResult(seats, ticket, movie.id)
    }
}
