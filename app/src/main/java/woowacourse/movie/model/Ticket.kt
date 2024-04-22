package woowacourse.movie.model

import java.io.Serializable

class Ticket(
    private val uiMovie: UiMovie,
    initialQuantity: Int = MINIMUM_QUANTITY,
) : Serializable {
    var quantity: Int = initialQuantity
        private set

    fun getTitle(): String {
        return uiMovie.title
    }

    fun getOpeningDay(): String {
        return uiMovie.openingDay
    }

    fun getPrice(): Int {
        return uiMovie.price
    }

    fun increaseQuantity() = quantity++

    fun decreaseQuantity() {
        quantity = (quantity - 1).coerceAtLeast(MINIMUM_QUANTITY)
    }

    companion object {
        const val MINIMUM_QUANTITY = 1
    }
}
