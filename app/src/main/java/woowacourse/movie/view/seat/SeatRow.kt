package woowacourse.movie.view.seat

import android.graphics.Color
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.children
import woowacourse.movie.view.seat.model.coord.Column
import woowacourse.movie.view.seat.model.coord.Coordination
import woowacourse.movie.view.seat.model.coord.Row

class SeatRow(
    private val row: TableRow,
    private val rowIndex: Int,
    private val onSeatClick: (Coordination) -> Unit,
) {
    private val seatViews = mutableMapOf<Coordination, TextView>()

    fun initSeats() {
        row.children
            .filterIsInstance<TextView>()
            .forEachIndexed { colIndex, view ->
                val coord = Coordination(Column(rowIndex + 1), Row(colIndex + 1))
                view.tag = coord
                seatViews[coord] = view
                view.setOnClickListener { onSeatClick(coord) }
            }
    }

    fun updateSeats(selectedCoords: Set<Coordination>) {
        seatViews.forEach { (coord, view) ->
            view.setBackgroundColor(
                if (coord in selectedCoords) Color.YELLOW else Color.TRANSPARENT,
            )
        }
    }
}
