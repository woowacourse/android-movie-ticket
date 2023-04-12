package woowacourse.movie.domain

import java.io.Serializable

class MovieTicket(
    val title: String,
    val time: MovieTime,
    val peopleCount: PeopleCount,
) : Serializable {
    fun getPrice(): Int = TICKET_PRICE * peopleCount.count

    companion object {
        private const val TICKET_PRICE = 13000
    }
}
