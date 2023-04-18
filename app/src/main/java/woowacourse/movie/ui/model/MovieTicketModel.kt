package woowacourse.movie.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.MovieTicket

fun mapToMovieTicketModel(movieTicket: MovieTicket): MovieTicketModel {
    return MovieTicketModel(
        movieTicket.title,
        mapToTicketTimeModel(movieTicket.time),
        mapToPeopleCountModel(movieTicket.peopleCount),
        movieTicket.getPrice()
    )
}

@Parcelize
data class MovieTicketModel(
    val title: String,
    val time: TicketTimeModel,
    val peopleCount: PeopleCountModel,
    val price: Int
) : Parcelable
