package woowacourse.movie.presentation.mapper

import woowacourse.movie.domain.model.seat.DomainSeatRow
import woowacourse.movie.presentation.model.SeatRow

fun SeatRow.toDomain(): DomainSeatRow =
    DomainSeatRow((value - 64).code)

fun DomainSeatRow.toPresentation(): SeatRow =
    SeatRow((value + 64).toChar())
