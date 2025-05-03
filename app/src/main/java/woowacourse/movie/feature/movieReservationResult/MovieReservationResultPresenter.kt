package woowacourse.movie.feature.movieReservationResult

import woowacourse.movie.domain.movie.Ticket
import woowacourse.movie.domain.seat.Seats
import woowacourse.movie.feature.model.movie.TicketUiModel
import woowacourse.movie.feature.model.seat.SeatsUiModel
import woowacourse.movie.feature.model.toDomain

class MovieReservationResultPresenter(
    private val view: MovieReservationResultContract.View,
) : MovieReservationResultContract.Presenter {
    private lateinit var ticket: Ticket
    private lateinit var seats: Seats

    override fun initializeReservationInfo(
        ticket: TicketUiModel,
        seats: SeatsUiModel,
    ) {
        this.ticket = ticket.toDomain()
        this.seats = seats.toDomain()

        val selectedSeats =
            seats.seats.joinToString { seat ->
                seat.row.toRowAlphabet() + seat.col.toColNumber()
            }
        view.showReservationInfo(ticket, selectedSeats)
        view.updateTotalPrice(this.seats.totalPrice())
    }

    private fun Int.toRowAlphabet(): Char {
        return (this + ROW_STARTING_VALUE).toChar()
    }

    private fun Int.toColNumber(): String {
        return (this + COL_STARTING_VALUE).toString()
    }

    companion object {
        private const val ROW_STARTING_VALUE = 'A'.code
        private const val COL_STARTING_VALUE = 1
    }
}
