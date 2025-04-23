package woowacourse.movie.domain.model

import java.io.Serializable
import java.time.LocalDateTime

class BookedTicket(
    val movieName: String,
    val headcount: Headcount,
    val time: LocalDateTime,
) : Serializable {
    fun totalPrice(): Int = headcount.price()
}
