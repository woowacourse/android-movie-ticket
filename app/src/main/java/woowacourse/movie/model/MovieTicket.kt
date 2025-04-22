package woowacourse.movie.model

import java.io.Serializable

data class MovieTicket(
    val title: String,
    val movieDate: MovieDate,
    val movieTime: MovieTime,
    val count: Int,
) : Serializable {
    fun price(): Int = count * TICKET_PRICE

    companion object {
        private const val TICKET_PRICE = 13000
    }
}
