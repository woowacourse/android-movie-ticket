package woowacourse.movie.presentation.model

import woowacourse.movie.domain.model.MovieSeat
import woowacourse.movie.domain.model.SeatType
import java.io.Serializable

class MovieSeatModel(
    val seatName: String,
    val seatType: SeatType,
) : Serializable

fun MovieSeat.toMovieSeatModel(): MovieSeatModel {
    return MovieSeatModel(
        seatName = seatName,
        seatType = seatType,
    )
}

fun MovieSeatModel.toMovieSeat(): MovieSeat {
    return MovieSeat(
        seatName = seatName,
        seatType = seatType,
    )
}
