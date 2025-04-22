package woowacourse.movie.domain

import java.time.LocalDateTime

data class BookingStatus(
    val movie: Movie,
    val isBooked: Boolean,
    val memberCount: MemberCount,
    val bookedTime: LocalDateTime,
) {
    fun calculateTicketPrices(): Int {
        return memberCount.value * TICKET_PRICE
    }

    companion object {
        private const val TICKET_PRICE = 13_000
    }
}
