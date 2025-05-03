package woowacourse.movie.view.seat.model

import woowacourse.movie.domain.seat.Seat

fun Seat.toUiModel(): SeatUiModel =
    SeatUiModel(
        row = this.row,
        col = this.col,
    )

fun SeatUiModel.toDomain(): Seat =
    Seat.from(
        row = this.row,
        col = this.col,
    )
