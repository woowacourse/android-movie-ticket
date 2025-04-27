package woowacourse.movie.presenter.movieReservationResult

import woowacourse.movie.R
import woowacourse.movie.domain.Seats
import woowacourse.movie.domain.Ticket
import woowacourse.movie.view.model.SeatsUiModel
import woowacourse.movie.view.model.TicketUiModel
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
    private lateinit var seats: Seats

    override fun loadReservationInfo() {
        ticket = view.intent.extras?.getParcelableCompat<TicketUiModel>(KEY_TICKET)
            ?.toDomain() ?: return
        seats = view.intent.extras?.getParcelableCompat<SeatsUiModel>(KEY_SEATS)
            ?.toDomain() ?: return

        val dateTimeFormatter = DateTimeFormatter.ofPattern(view.getString(R.string.date_time_format))
        view.showMovieDateTime(ticket.showtime.format(dateTimeFormatter))

        val ticketCountTemplate = view.getString(R.string.ticket_count_format)
        view.showTicketCount(ticketCountTemplate.format(ticket.count.value))

        val selectedSeats =
            seats.seats.joinToString { seat ->
                seat.row.toRowAlphabet() + seat.col.toColNumber()
            }
        view.showSelectedSeats(selectedSeats)

        val totalPriceTemplate = view.getString(R.string.ticket_price_format)
        view.showTotalPrice(totalPriceTemplate.format(seats.totalPrice()))
    }

    private fun Int.toRowAlphabet(): Char {
        return (this + 'A'.code).toChar()
    }

    private fun Int.toColNumber(): String {
        return (this + 1).toString()
    }
}
