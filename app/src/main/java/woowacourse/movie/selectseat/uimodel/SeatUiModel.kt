package woowacourse.movie.selectseat.uimodel

import androidx.annotation.ColorRes
import woowacourse.movie.R
import woowacourse.movie.model.SeatRate

data class SeatUiModel(
    val position: String,
    @ColorRes val color: Int,
) {
    constructor(row: Int, col: Int, seatRate: SeatRate) : this(position(row, col), color(seatRate))

    companion object {
        private fun position(
            row: Int,
            col: Int,
        ): String {
            val rowLetter = 'A' + (row - 1)
            return "$rowLetter$col"
        }

        private fun color(rate: SeatRate): Int =
            when (rate) {
                SeatRate.S -> R.color.green
                SeatRate.A -> R.color.blue
                SeatRate.B -> R.color.purple
            }
    }
}
