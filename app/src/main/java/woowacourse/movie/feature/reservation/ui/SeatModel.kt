package woowacourse.movie.feature.reservation.ui

import woowacourse.movie.domain.seat.Seat

data class SeatModel(
    val row: String,
    val col: Int,
    val grade: String,
    val price: Long,
) {
    fun joinRowColText(): String = row + col.toString()
}

fun Seat.toUiModel(): SeatModel =
    SeatModel(
        row = row,
        col = col,
        grade = grade.name,
        price = price,
    )
