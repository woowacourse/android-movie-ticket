package woowacourse.movie.model.ticket

import woowacourse.movie.model.ticket.seat.Seat
import woowacourse.movie.model.ticket.seat.grade.SeatGradePolicy

@JvmInline
value class TicketPrice(
    val value: Int,
) {
    companion object {
        fun calculateTotalPrice(
            seats: Set<Seat>,
            seatGradePolicy: SeatGradePolicy,
        ): TicketPrice = TicketPrice(seats.sumOf { seatGradePolicy.getGrade(it).ticketPrice.value })
    }
}
