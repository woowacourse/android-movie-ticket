package woowacourse.movie.model

import java.io.Serializable

data class Ticket(
    val movie: Movie,
    val quantity: Int,
    val price: Int = 13000,
) : Serializable {
    fun getTitle(): String {
        return movie.title
    }

    fun getOpeningDay(): String {
        return movie.openingDay
    }
}
