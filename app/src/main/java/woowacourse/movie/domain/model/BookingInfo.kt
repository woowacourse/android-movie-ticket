package woowacourse.movie.domain.model

import java.time.LocalDate

data class BookingInfo(
    val movie: Movie,
    val date: LocalDate,
    val movieTime: MovieTime,
    val ticketCount: Int,
    val eachPrice: Int = DEFAULT_TICKET_PRICE,
) {
    val totalPrice: Int
        get() = eachPrice * ticketCount

    companion object {
        private const val DEFAULT_TICKET_PRICE = 13_000
    }
}
