package woowacourse.movie.domain.model

import java.time.LocalDateTime

data class MovieTicket(
    val id: Int,
    val movieTitle: String,
    val screeningDate: LocalDateTime,
    val reservationCount: Int,
    val reservationSeats: Seats = Seats(emptyList()),
) {
    fun reserveSeats(seats: List<Seat>) = copy(reservationSeats = Seats(seats))

    fun totalPrice() = reservationSeats.totalPrice()
}
