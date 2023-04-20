package woowacourse.movie.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.MovieTicket
import woowacourse.movie.ui.model.seat.SeatModel
import woowacourse.movie.ui.model.seat.mapToSeat
import woowacourse.movie.ui.model.seat.mapToSeatModel

fun mapToMovieTicket(movieTicket: MovieTicketModel): MovieTicket {
    return MovieTicket(
        movieTicket.title,
        mapToTicketTime(movieTicket.time),
        mapToPeopleCount(movieTicket.peopleCount),
        movieTicket.seats.map { mapToSeat(it) }.toMutableSet(),
        mapToPrice(movieTicket.price)
    )
}

fun mapToMovieTicketModel(movieTicket: MovieTicket): MovieTicketModel {
    return MovieTicketModel(
        movieTicket.title,
        mapToTicketTimeModel(movieTicket.time),
        mapToPeopleCountModel(movieTicket.peopleCount),
        movieTicket.seats.map { mapToSeatModel(it) }.toSet(),
        mapToPriceModel(movieTicket.getDiscountPrice())
    )
}

fun mapToMovieTicketModelWithOriginalPrice(movieTicket: MovieTicket): MovieTicketModel {
    return MovieTicketModel(
        movieTicket.title,
        mapToTicketTimeModel(movieTicket.time),
        mapToPeopleCountModel(movieTicket.peopleCount),
        movieTicket.seats.map { mapToSeatModel(it) }.toSet(),
        mapToPriceModel(movieTicket.price)
    )
}

@Parcelize
data class MovieTicketModel(
    val title: String,
    val time: TicketTimeModel,
    val peopleCount: PeopleCountModel,
    val seats: Set<SeatModel>,
    val price: PriceModel
) : Parcelable {
    fun isSelectedSeat(seat: SeatModel): Boolean = seats.contains(seat)
}
