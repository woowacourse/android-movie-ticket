package woowacourse.movie.mapper

import com.example.domain.model.model.Seat
import woowacourse.movie.model.RowSeat
import woowacourse.movie.model.SeatModel

private const val NOT_NUMBER_ERROR = "숫자가 아닙니다."

fun Seat.toSeatModel() = SeatModel(
    RowSeat.of(row),
    (column + 1).toString()
)

fun SeatModel.toSeat() = Seat(
    RowSeat.valueOf(row).ordinal,
    column.toIntOrNull()?.minus(1) ?: throw IllegalArgumentException(NOT_NUMBER_ERROR)
)
