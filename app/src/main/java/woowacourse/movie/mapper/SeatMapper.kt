package woowacourse.movie.mapper

import com.example.domain.model.model.Seat
import woowacourse.movie.model.RowSeat
import woowacourse.movie.model.SeatModel

fun Seat.toSeatModel() = SeatModel(
    RowSeat.of(row),
    (column + 1).toString()
)
