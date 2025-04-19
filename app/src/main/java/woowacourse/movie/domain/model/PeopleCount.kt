package woowacourse.movie.domain.model

import java.io.Serializable

@JvmInline
value class PeopleCount(
    val count: Int,
) : Serializable {
    fun ticketPrice(type: TicketType): Int = type.price * count
}
