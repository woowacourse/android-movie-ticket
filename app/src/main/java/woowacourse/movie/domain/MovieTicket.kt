package woowacourse.movie.domain

import java.io.Serializable

class MovieTicket(
    val title: String,
    val time: MovieTime,
    val peopleCount: PeopleCount,
) : Serializable {
    fun getPrice(): Int {
        var price = TICKET_PRICE * peopleCount.count
        if (time.isMovieDay()) price = (price * 0.9).toInt()
        return price
    }

    companion object {
        private const val TICKET_PRICE = 13000
    }
}
