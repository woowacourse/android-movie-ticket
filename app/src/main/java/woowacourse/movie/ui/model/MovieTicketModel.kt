package woowacourse.movie.ui.model

import woowacourse.movie.domain.MovieTicket
import java.io.Serializable

fun mapToMovieTicketModel(movieTicket: MovieTicket): MovieTicketModel {
    return MovieTicketModel(
        movieTicket.title,
        mapToTicketTimeModel(movieTicket.time),
        mapToPeopleCountModel(movieTicket.peopleCount),
        movieTicket.getPrice()
    )
}

data class MovieTicketModel(
    val title: String,
    val time: TicketTimeModel,
    val peopleCount: PeopleCountModel,
    val price: Int
) : Serializable
