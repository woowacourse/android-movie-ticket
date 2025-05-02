package woowacourse.movie.view.seat.manager

import android.widget.TableLayout
import android.widget.TableRow
import androidx.core.view.children
import woowacourse.movie.domain.model.seat.Seat
import woowacourse.movie.view.seat.SeatRow

class SeatView(
    private val seatTable: TableLayout,
    private val onSeatClick: (Seat) -> Unit,
) {
    private val rows = mutableListOf<SeatRow>()

    fun initSeats() {
        seatTable.children
            .filterIsInstance<TableRow>()
            .forEachIndexed { rowIndex, row ->
                val rowManager = SeatRow(row, rowIndex, onSeatClick)
                rowManager.initSeats()
                rows.add(rowManager)
            }
    }

    fun updateSeats(selectedSeats: Set<Seat>) {
        rows.forEach { it.updateSeats(selectedSeats) }
    }
}
