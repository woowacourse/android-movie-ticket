package woowacourse.movie.presentation.mapper

import woowacourse.movie.domain.model.seat.DomainSeatCol
import woowacourse.movie.presentation.model.SeatColumn

fun SeatColumn.toDomain(): DomainSeatCol =
    DomainSeatCol(value)

fun DomainSeatCol.toPresentation(): SeatColumn =
    SeatColumn(value)
