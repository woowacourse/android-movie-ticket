package woowacourse.movie.presentation.mapper

import woowacourse.movie.domain.model.seat.DomainSeatClass
import woowacourse.movie.presentation.model.SeatClass

fun SeatClass.toDomain(): DomainSeatClass = when (this) {
    SeatClass.S -> DomainSeatClass.S
    SeatClass.A -> DomainSeatClass.A
    SeatClass.B -> DomainSeatClass.B
}

fun DomainSeatClass.toPresentation(): SeatClass = when (this) {
    DomainSeatClass.S -> SeatClass.S
    DomainSeatClass.A -> SeatClass.A
    DomainSeatClass.B -> SeatClass.B
}
