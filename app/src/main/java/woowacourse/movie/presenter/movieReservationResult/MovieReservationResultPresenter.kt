package woowacourse.movie.presenter.movieReservationResult

import woowacourse.movie.domain.movie.Ticket
import woowacourse.movie.domain.seat.Seats
import woowacourse.movie.view.model.movie.TicketUiModel
import woowacourse.movie.view.model.seat.SeatsUiModel
import woowacourse.movie.view.model.toDomain

class MovieReservationResultPresenter(
    private val view: MovieReservationResultContract.View,
) : MovieReservationResultContract.Presenter {
    private lateinit var ticket: Ticket
    private lateinit var seats: Seats

    override fun onViewCreated(
        ticket: TicketUiModel,
        seats: SeatsUiModel,
    ) {
        this.ticket = ticket.toDomain()
        this.seats = seats.toDomain()

        val selectedSeats =
            seats.seats.joinToString { seat ->
                seat.row.toRowAlphabet() + seat.col.toColNumber()
            }
        view.showMovieTitle(this.ticket.movie.title)
        view.showMovieDateTime(this.ticket.showtime)
        view.showTicketCount(this.ticket.count.value)
        view.showSelectedSeats(selectedSeats)
        view.showTotalPrice(this.seats.totalPrice())
    }

    private fun Int.toRowAlphabet(): Char {
        return (this + 'A'.code).toChar()
    }

    private fun Int.toColNumber(): String {
        return (this + 1).toString()
    }
}
