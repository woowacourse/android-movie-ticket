package woowacourse.movie.selectseat.uimodel

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import woowacourse.movie.model.SeatRate

@Parcelize
data class SeatUiModel(
    val showPosition: String,
    val rateColor: RateColor,
    val row: Int,
    val col: Int,
    val state: SeatState = SeatState.NONE,
) : Parcelable {
    constructor(row: Int, col: Int, seatRate: SeatRate) : this(
        positionFormat(row, col),
        color(seatRate),
        row,
        col,
    )

    fun changeState(): SeatUiModel = SeatUiModel(showPosition, rateColor, row, col, state.reserveState())

    companion object {
        private fun positionFormat(
            row: Int,
            col: Int,
        ): String {
            val rowLetter = 'A' + row
            return "$rowLetter${col + 1}"
        }

        private fun color(rate: SeatRate): RateColor =
            when (rate) {
                SeatRate.S -> RateColor.GREEN
                SeatRate.A -> RateColor.BLUE
                SeatRate.B -> RateColor.PURPLE
            }
    }
}
