package woowacourse.movie.model

import woowacourse.movie.Reservation
import woowacourse.movie.SelectedSeat
import woowacourse.movie.ticket.Position
import woowacourse.movie.ticket.Seat
import woowacourse.movie.ticket.Ticket

object Mapper {
    fun Ticket.toUiModel(): TicketUiModel {
        return TicketUiModel(
            movieId = this.movieId,
            bookedDateTime = this.bookedDateTime,
            seat = this.seat.toUiModel(),
        )
    }

    fun Seat.toUiModel(): SeatUiModel {
        return SeatUiModel(
            rank = this.rank,
            position = this.position.toUiModel(),
        )
    }

    fun SeatUiModel.toDomainModel(): Seat {
        return Seat(
            rank = this.rank,
            position = this.position.toDomainModel(),
        )
    }

    fun Position.toUiModel(): PositionUiModel {
        return PositionUiModel(
            row = this.row,
            column = this.column,
        )
    }

    fun PositionUiModel.toDomainModel(): Position {
        return Position(
            row = this.row,
            column = this.column,
        )
    }

    fun Reservation.toUiModel(): ReservationUiModel {
        return ReservationUiModel(
            tickets = this.tickets.map { it.toUiModel() }.toSet(),
            paymentType = this.paymentType,
            payment = this.payment,
            movieId = this.movieId,
            bookedDateTime = this.bookedDateTime,
            count = this.count,
        )
    }

    fun SelectedSeat.toUiModel(): SelectedSeatUiModel {
        return SelectedSeatUiModel(this.seats.map { it.toUiModel() }.toSet())
    }
}
