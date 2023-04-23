package woowacourse.movie.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.domain.MovieTicket
import woowacourse.movie.ui.model.seat.SeatModel
import woowacourse.movie.ui.model.seat.mapToSeat
import woowacourse.movie.ui.model.seat.mapToSeatModel

fun MovieTicketModel.mapToMovieTicket(): MovieTicket {
    return MovieTicket(
        title,
        time.mapToTicketTime(),
        peopleCount.mapToPeopleCount(),
        seats.map { it.mapToSeat() }.toSet(),
        price.mapToPrice()
    )
}

fun MovieTicket.mapToMovieTicketModel(): MovieTicketModel {
    return MovieTicketModel(
        title,
        time.mapToTicketTimeModel(),
        peopleCount.mapToPeopleCountModel(),
        seats.map { it.mapToSeatModel() }.toSet(),
        getDiscountPrice().mapToPriceModel()
    )
}

fun MovieTicket.mapToMovieTicketModelWithOriginalPrice(): MovieTicketModel {
    return MovieTicketModel(
        title,
        time.mapToTicketTimeModel(),
        peopleCount.mapToPeopleCountModel(),
        seats.map { it.mapToSeatModel() }.toSet(),
        price.mapToPriceModel()
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
