package woowacourse.movie.model.ticketing

import woowacourse.movie.model.theater.SeatClass

class Tickets(val value: List<Ticket>) {
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
