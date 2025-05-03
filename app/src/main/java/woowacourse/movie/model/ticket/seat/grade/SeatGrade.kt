package woowacourse.movie.model.ticket.seat.grade

import woowacourse.movie.model.ticket.TicketPrice

sealed class SeatGrade(
    val ticketPrice: TicketPrice,
) {
    data object B : SeatGrade(TicketPrice(10_000))

    data object S : SeatGrade(TicketPrice(15_000))

    data object A : SeatGrade(TicketPrice(12_000))
}
