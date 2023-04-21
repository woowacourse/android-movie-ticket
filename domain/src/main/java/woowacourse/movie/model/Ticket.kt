package woowacourse.movie.model

import java.time.LocalDateTime

data class Ticket(
    val movieId: Long,
    val bookedDateTime: LocalDateTime,
    val count: Int,
    val seats: List<Seat>,
) {
    fun getPaymentMoney(): Money = Seats(seats).getPaymentMoney(bookedDateTime)
}
