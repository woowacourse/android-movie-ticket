package woowacourse.movie.view.seat.model

import androidx.annotation.ColorRes
import woowacourse.movie.R
import woowacourse.movie.domain.seat.SeatGrade

data class SeatUiModel(
    val row: Int,
    val col: Int,
) {
    private val grade: SeatGrade = SeatGrade.of(row)

    @ColorRes
    val colorResId: Int =
        when (grade) {
            SeatGrade.S -> R.color.green
            SeatGrade.A -> R.color.blue
            SeatGrade.B -> R.color.purple
        }
}
