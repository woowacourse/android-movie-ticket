package woowacourse.movie.view.seat.manager

import android.widget.TableLayout
import android.widget.TableRow
import androidx.core.view.children
import woowacourse.movie.domain.model.seat.Seat
import woowacourse.movie.view.seat.SeatRow
import woowacourse.movie.view.seat.model.coord.Column
import woowacourse.movie.view.seat.model.coord.Coordination
import woowacourse.movie.view.seat.model.coord.Row

class SeatView(
    private val seatTable: TableLayout,
    private val onSeatClick: (Coordination) -> Unit,
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
        val selectedCoords = selectedSeats.map { Coordination(Column(it.x), Row(it.y)) }.toSet()
        rows.forEach { it.updateSeats(selectedCoords) }
    }
}
