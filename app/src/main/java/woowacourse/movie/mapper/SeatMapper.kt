package woowacourse.movie.mapper

import com.woowacourse.domain.seat.Seat
import woowacourse.movie.model.SeatModel

fun Seat.toPresentation(): SeatModel =
    SeatModel(row.toPresentation(), column.toPresentation())

fun SeatModel.toDomain(): Seat =
    Seat(row.toDomain(), column.toDomain())
