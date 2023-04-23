package woowacourse.movie.model.mapper

import movie.domain.seat.Seat
import woowacourse.movie.model.SeatState

fun MutableSet<SeatState>.toDomain(): MutableSet<Seat> = map { it.toDomain() }.toMutableSet()

fun MutableSet<Seat>.toPresentation(): MutableSet<SeatState> = map { it.toPresentation() }.toMutableSet()

fun SeatState.toDomain(): Seat = Seat(index)

fun Seat.toPresentation(): SeatState = SeatState(index)
