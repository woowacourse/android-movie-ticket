package woowacourse.movie

import java.io.Serializable

class Ticket(
    val title: String,
    val date: Long,
    val count: Int,
) : Serializable {
    val price: Int = TICKET_PRICE * count

    companion object {
        const val TICKET_PRICE = 13_000
    }
}
