package woowacourse.movie.model

import java.io.Serializable

class Ticket : Serializable {
    var count: Int = DEFAULT_TICKET_COUNT
        private set

    fun increaseCount(): Result {
        if (count >= MAX_TICKET_COUNT) return Failure
        count++
        return Success
    }

    fun decreaseCount(): Result {
        if (count <= MIN_TICKET_COUNT) return Failure
        count--
        return Success
    }

    fun calculatePrice(): Int = count * PRICE

    companion object {
        private const val PRICE = 13_000
        private const val DEFAULT_TICKET_COUNT = 1
        private const val MAX_TICKET_COUNT = 100
        private const val MIN_TICKET_COUNT = 1
    }
}
