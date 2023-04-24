package woowacourse.movie.presentation.mapper

import woowacourse.movie.domain.model.seat.DomainSeat
import woowacourse.movie.presentation.model.Seat

fun Seat.toDomain(): DomainSeat =
    DomainSeat(row.toDomain(), col.toDomain())

fun DomainSeat.toPresentation(): Seat =
    Seat(row.toPresentation(), col.toPresentation())
