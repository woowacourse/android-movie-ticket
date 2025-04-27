package woowacourse.movie.model.ticket.seat.grade

import woowacourse.movie.model.ticket.TicketPrice

sealed class SeatGrade(
    val ticketPrice: TicketPrice,
) {
    object B : SeatGrade(TicketPrice(10_000))

    object S : SeatGrade(TicketPrice(15_000))

    object A : SeatGrade(TicketPrice(12_000))
}
