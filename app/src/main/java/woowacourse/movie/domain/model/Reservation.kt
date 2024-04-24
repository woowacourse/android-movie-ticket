package woowacourse.movie.domain.model

import java.time.LocalDateTime

data class Reservation(
    val id: Int,
    val movie: Movie,
    val ticketCount: Int,
    val seats: List<Seat>,
    val dateTime: LocalDateTime,
) {
    val totalPrice: Int
        get() = seats.sumOf { seat -> seat.seatRank.price }
}
