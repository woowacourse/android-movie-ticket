package woowacourse.movie.feature.seat.ui

import androidx.annotation.ColorRes
import woowacourse.movie.R
import woowacourse.movie.model.seat.Seat
import woowacourse.movie.model.seat.SeatRating
import woowacourse.movie.model.seat.SeatTable

data class SeatSelectTableUiModel(
    val seatMessage: String,
    @ColorRes
    val seatColorId: Int,
) {
    companion object {
        fun from(seatTable: SeatTable): List<List<SeatSelectTableUiModel>> {
            return seatTable.table.map { row ->
                row.map { seat ->
                    SeatSelectTableUiModel(seat.message(), SeatRating.from(seat).colorId())
                }
            }
        }

        private fun Seat.message() = "${(row + 'A'.code - 1).toChar()}$col"

        private fun SeatRating.colorId(): Int {
            return when (this) {
                SeatRating.B -> R.color.purple
                SeatRating.S -> R.color.green
                SeatRating.A -> R.color.blue
            }
        }
    }
}
