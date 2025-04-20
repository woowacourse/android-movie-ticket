package woowacourse.movie.domain.model

import java.io.Serializable

class BookedTicket(
    val movieName: String,
    val headcount: Headcount,
    val time: String,
) : Serializable {
    fun ticketPrice(type: TicketType): Int = type.price * headcount.count
}
