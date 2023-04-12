package woowacourse.movie.domain

import java.io.Serializable
import java.time.LocalDate

class MovieTicket(
    val title: String,
    val date: LocalDate,
    val time: MovieTime,
    val peopleCount: PeopleCount,
) : Serializable {
    fun getPrice(): Int = TICKET_PRICE * peopleCount.count

    companion object {
        private const val TICKET_PRICE = 13000
    }
}
