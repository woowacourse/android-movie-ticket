package woowacourse.movie.presenter.movieReservationResult

import woowacourse.movie.R
import woowacourse.movie.domain.movie.Ticket
import woowacourse.movie.domain.theater.Theater
import woowacourse.movie.view.model.movie.TicketUiModel
import woowacourse.movie.view.model.theater.TheaterUiModel
import woowacourse.movie.view.model.toDomain
import woowacourse.movie.view.movieReservationResult.MovieReservationResultActivity
import woowacourse.movie.view.movieReservationResult.MovieReservationResultActivity.Companion.KEY_SEATS
import woowacourse.movie.view.movieReservationResult.MovieReservationResultActivity.Companion.KEY_TICKET
import woowacourse.movie.view.utils.getParcelableCompat
import java.time.format.DateTimeFormatter

class MovieReservationResultPresenter(
    private val view: MovieReservationResultActivity,
) : MovieReservationResultContract.Presenter {
    private lateinit var ticket: Ticket
    private lateinit var theater: Theater

    override fun onViewCreated() {
        ticket = view.intent.extras?.getParcelableCompat<TicketUiModel>(KEY_TICKET)
            ?.toDomain() ?: return
        theater = view.intent.extras?.getParcelableCompat<TheaterUiModel>(KEY_SEATS)
            ?.toDomain() ?: return

        val dateTimeFormatter = DateTimeFormatter.ofPattern(view.getString(R.string.format_date_time))
        val ticketCountTemplate = view.getString(R.string.template_ticket_count)
        val selectedSeats =
            theater.seats.joinToString { seat ->
                seat.row.toRowAlphabet() + seat.col.toColNumber()
            }
        val totalPriceTemplate = view.getString(R.string.template_price)

        view.showMovieDateTime(ticket.showtime.format(dateTimeFormatter))
        view.showTicketCount(ticketCountTemplate.format(ticket.count.value))
        view.showSelectedSeats(selectedSeats)
        view.showTotalPrice(totalPriceTemplate.format(theater.totalPrice()))
    }

    private fun Int.toRowAlphabet(): Char {
        return (this + 'A'.code).toChar()
    }

    private fun Int.toColNumber(): String {
        return (this + 1).toString()
    }
}
