package woowacourse.movie.model

import woowacourse.movie.domain.seat.Seat

fun Seat.toPresentation(): SeatModel {
    return SeatModel(row, col)
}
