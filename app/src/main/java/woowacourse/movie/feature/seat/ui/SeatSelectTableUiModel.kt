package woowacourse.movie.feature.seat.ui

import woowacourse.movie.R
import woowacourse.movie.model.Seat
import woowacourse.movie.model.SeatRating
import woowacourse.movie.model.Seats

class SeatSelectTableUiModel(
    val seatMessage: String,
    val seatColorId: Int,
) {
    companion object {
        fun from(seats: Seats): List<List<SeatSelectTableUiModel>> {
            return seats.table.map { row ->
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
