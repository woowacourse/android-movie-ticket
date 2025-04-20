package woowacourse.movie.domain.model

import java.io.Serializable

class Headcount(
    count: Int = 1,
    val ticketType: TicketType = TicketType.GENERAL,
) : Serializable {
    var count: Int = count
        private set

    fun ticketPrice(type: TicketType): Int = type.price * count

    fun increase() = count++

    fun decrease() = count--
}
