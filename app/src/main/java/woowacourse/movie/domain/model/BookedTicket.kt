package woowacourse.movie.domain.model

import java.io.Serializable
import java.time.LocalDateTime

class BookedTicket(
    val movieName: String,
    val headcount: Headcount,
    val dateTime: LocalDateTime,
) : Serializable {
    fun totalPrice(): Int = headcount.price()
}
