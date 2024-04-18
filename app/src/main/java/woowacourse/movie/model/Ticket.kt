package woowacourse.movie.model

import java.io.Serializable

class Ticket : Serializable {
    var count: Int = 1
        private set

    fun increaseCount(): Result {
        if (count >= 100) return Failure
        count += 1
        return Success
    }

    fun decreaseCount(): Result {
        if (count <= 1) return Failure
        count -= 1
        return Success
    }

    fun calculatePrice(): Int = count * PRICE

    companion object {
        private const val PRICE = 13_000
    }
}
