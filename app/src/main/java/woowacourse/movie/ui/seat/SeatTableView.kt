package woowacourse.movie.ui.seat

import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.domain.seat.SelectedSeats
import woowacourse.movie.mapper.toDomain
import woowacourse.movie.model.SeatModel

class SeatTableView(
    private val seatTable: TableLayout,
    private val clickEvent: (Seat, View) -> Unit,
) {
    private val context = seatTable.context
    private var selectedSeats = SelectedSeats()

    fun init() {
        for (row in 1..SeatSelectionActivity.ROW_SIZE) {
            val tableRow = TableRow(context)
            for (column in 1..SeatSelectionActivity.COLUMN_SIZE) {
                tableRow.addView(getSeatView(row, column))
            }
            seatTable.addView(tableRow)
        }
    }

    private fun getSeatView(row: Int, column: Int): View {
        val seat = SeatModel(row, column)
        return seat.getView(
            context,
            selectedSeats.contains(seat.toDomain()),
        ) {
            clickEvent(seat.toDomain(), this)
        }
    }
}
