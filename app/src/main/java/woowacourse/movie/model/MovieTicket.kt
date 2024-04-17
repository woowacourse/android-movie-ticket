package woowacourse.movie.model

import java.io.Serializable

class MovieTicket(
    val title: String,
    val date: Long,
    val count: Int,
) : Serializable {
    val price: Int = TICKET_PRICE * count

    companion object {
        const val TICKET_PRICE = 13_000
    }
}
