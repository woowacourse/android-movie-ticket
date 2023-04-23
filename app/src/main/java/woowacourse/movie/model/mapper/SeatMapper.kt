package woowacourse.movie.model.mapper

import movie.domain.seat.Seat
import woowacourse.movie.model.SeatState

fun MutableList<SeatState>.toDomain(): MutableList<Seat> = map { it.toDomain() }.toMutableList()

fun MutableList<Seat>.toPresentation(): MutableList<SeatState> = map { it.toPresentation() }.toMutableList()

fun SeatState.toDomain(): Seat = Seat(index)

fun Seat.toPresentation(): SeatState = SeatState(index)
