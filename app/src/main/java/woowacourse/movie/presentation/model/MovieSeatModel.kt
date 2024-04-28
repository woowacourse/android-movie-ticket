package woowacourse.movie.presentation.model

import woowacourse.movie.domain.model.MovieSeat
import woowacourse.movie.domain.model.SeatType
import java.io.Serializable

class MovieSeatModel(
    val seatRow: String,
    val seatColumn: Int,
    val seatType: SeatType,
) : Serializable

fun MovieSeat.toMovieSeatModel(): MovieSeatModel {
    return MovieSeatModel(
        seatRow = seatRow,
        seatColumn = seatColumn,
        seatType = seatType,
    )
}

fun MovieSeatModel.toMovieSeat(): MovieSeat {
    return MovieSeat(
        seatRow = seatRow,
        seatColumn = seatColumn,
        seatType = seatType,
    )
}
