package woowacourse.movie.model

import woowacourse.movie.model.ticket.BookingSeat
import woowacourse.movie.model.ticket.Ticket

class Tickets(
    private val count: Count,
    val movie: Movie,
) {
    val totalPrice: Int
        get() = count.value * TICKET_PRICE

    companion object {
        private const val TICKET_PRICE = 13000
    }
}

class Tickets2(val value: List<Ticket>) {
    init {
        val bookedSeats = value.map(Ticket::bookingSeat)
        require(bookedSeats.size == bookedSeats.distinct().size) { ERROR_DUPLICATED_SEAT }
    }

    val totalPrice: Int
        get() =
            value
                .map(Ticket::bookingSeat)
                .map(BookingSeat::seatClass)
                .map(SeatClass::price)
                .sum()

    companion object {
        private const val ERROR_DUPLICATED_SEAT = "예약 좌석은 중복될 수 없습니다."
    }
}
