package woowacourse.movie.mapper

import com.woowacourse.domain.seat.SeatRow
import woowacourse.movie.model.SeatRowModel

fun SeatRow.toPresentation() = SeatRowModel(value)

fun SeatRowModel.toDomain() = SeatRow(value)
