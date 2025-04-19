package woowacourse.movie.domain.model

import java.io.Serializable

class PeopleCount(
    count: Int = 1,
) : Serializable {
    var count: Int = count
        private set

    fun ticketPrice(type: TicketType): Int = type.price * count

    fun increase() = count++

    fun decrease() = count--
}
