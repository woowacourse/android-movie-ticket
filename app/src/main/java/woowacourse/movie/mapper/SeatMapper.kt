package woowacourse.movie.mapper

import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.model.SeatModel

fun Seat.toModel() = SeatModel(
    row = row,
    column = column
)

fun SeatModel.toDomain() = Seat(
    row = row,
    column = column
)
