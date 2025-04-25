package woowacourse.movie.domain.model

import java.io.Serializable

class Headcount(
    count: Int = 1,
    val ticketType: TicketType = TicketType.GENERAL,
) : Serializable {
    var count: Int = count
        private set

    fun deepCopy(): Headcount = Headcount(count, ticketType)

    fun price(): Int = ticketType.price * count

    fun increase() {
        if (count < MAX_HEADCOUNT) {
            count++
        }
    }

    fun decrease() {
        if (count > MIN_HEADCOUNT) {
            count--
        }
    }

    companion object {
        const val MIN_HEADCOUNT = 1
        const val MAX_HEADCOUNT = 100
    }
}
