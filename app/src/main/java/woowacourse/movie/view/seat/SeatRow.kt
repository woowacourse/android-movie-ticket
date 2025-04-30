package woowacourse.movie.view.seat

import android.graphics.Color
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.children
import woowacourse.movie.domain.model.seat.Column
import woowacourse.movie.domain.model.seat.Row
import woowacourse.movie.domain.model.seat.Seat

class SeatRow(
    private val row: TableRow,
    private val rowIndex: Int,
    private val onSeatClick: (Seat) -> Unit,
) {
    private val seatViews = mutableMapOf<Seat, TextView>()

    fun initSeats() {
        row.children
            .filterIsInstance<TextView>()
            .forEachIndexed { colIndex, view ->
                val coord = Seat(Column(rowIndex + 1), Row(colIndex + 1))
                view.tag = coord
                seatViews[coord] = view
                view.setOnClickListener { onSeatClick(coord) }
            }
    }

    fun updateSeats(selectedCoords: Set<Seat>) {
        seatViews.forEach { (coord, view) ->
            view.setBackgroundColor(
                if (coord in selectedCoords) Color.YELLOW else Color.TRANSPARENT,
            )
        }
    }
}
