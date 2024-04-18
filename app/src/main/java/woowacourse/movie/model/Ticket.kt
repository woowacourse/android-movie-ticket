package woowacourse.movie.model

import java.io.Serializable

class Ticket(
    private val movie: Movie,
    initialQuantity: Int = MINIMUM_QUANTITY,
) : Serializable {
    var quantity: Int = initialQuantity
        private set

    fun getTitle(): String {
        return movie.title
    }

    fun getOpeningDay(): String {
        return movie.openingDay
    }

    fun getPrice(): Int {
        return movie.price
    }

    fun increaseQuantity() = quantity++

    fun decreaseQuantity() {
        quantity = (quantity - 1).coerceAtLeast(MINIMUM_QUANTITY)
    }

    companion object {
        const val MINIMUM_QUANTITY = 1
    }
}
