package woowacourse.movie.domain.model

import java.io.Serializable

class Tickets(private val tickets: List<Ticket>) : Serializable {
    val count get() = tickets.size

    init {
        require(tickets.size >= MINIMUM_COUNT) { INVALID_COUNT }
    }

    fun totalPrice() = tickets.sumOf { it.price }

    companion object {
        private const val INVALID_COUNT = "예약 개수는 1보다 같거나 커야 합니다."
        private const val MINIMUM_COUNT = 1
    }
}
