package woowacourse.movie.domain.model

import java.time.LocalDate

class MovieTicket(
    val id: Int,
    val movieTitle: String,
    val screeningDate: LocalDate,
    val reservationCount: Int,
) {
    fun totalPrice() = reservationCount * PRICE_PER_TICKET

    companion object {
        const val PRICE_PER_TICKET = 13000
    }
}
