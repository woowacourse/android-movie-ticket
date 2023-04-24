package woowacourse.movie.presentation.mapper

import woowacourse.movie.domain.model.seat.DomainPickedSeats
import woowacourse.movie.presentation.model.PickedSeats

fun PickedSeats.toDomain(): DomainPickedSeats =
    DomainPickedSeats(seats.map { it.toDomain() })

fun DomainPickedSeats.toPresentation(): PickedSeats =
    PickedSeats(seats.map { it.toPresentation() })
