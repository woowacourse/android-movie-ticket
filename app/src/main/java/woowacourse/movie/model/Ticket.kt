package woowacourse.movie.model

import java.io.Serializable

class Ticket(
    val movie: Movie,
    var quantity: Int = MINIMUM_QUANTITY,
) : Serializable {
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
