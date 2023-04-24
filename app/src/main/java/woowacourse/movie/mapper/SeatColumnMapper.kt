package woowacourse.movie.mapper

import com.woowacourse.domain.seat.SeatColumn
import woowacourse.movie.model.SeatColumnModel

fun SeatColumn.toPresentation() = SeatColumnModel(value)

fun SeatColumnModel.toDomain() = SeatColumn(value)
