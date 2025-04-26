package woowacourse.movie.domain.model

import java.io.Serializable

data class Seat(
    val row: Int,
    val col: Int,
    val ticketType: TicketType,
) : Serializable {
    fun price(): Int = ticketType.price
}
