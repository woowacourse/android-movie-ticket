package woowacourse.movie.ui.dto

import java.io.Serializable

fun mapToUIMovieTicket(movieTicket: woowacourse.movie.domain.MovieTicket): MovieTicket {
    return MovieTicket(
        movieTicket.title,
        mapToUITicketTime(movieTicket.time),
        mapToUIPeopleCount(movieTicket.peopleCount),
        movieTicket.getPrice()
    )
}

data class MovieTicket(
    val title: String,
    val time: TicketTime,
    val peopleCount: PeopleCount,
    val price: Int
) : Serializable
