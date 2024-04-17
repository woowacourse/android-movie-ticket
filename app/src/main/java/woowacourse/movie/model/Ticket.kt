package woowacourse.movie.model

import java.io.Serializable

data class Ticket(
    val movie: Movie,
    val quantity: Int,
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
}
