package woowacourse.movie.view.widget

import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import woowacourse.movie.R
import woowacourse.movie.view.data.SeatTable
import woowacourse.movie.view.mapper.SeatMapper.toView

class SeatTableLayout(
    private val tableLayout: TableLayout,
) {
    fun selectedSeats(): List<SeatView> {
        TODO("")
    }

    fun makeSeatTable(seats: SeatTable) {
        val tableLayout = tableLayout.findViewById<TableLayout>(R.id.seat_selection_table)
        tableLayout.weightSum = seats.size.row.toFloat()
        (0 until seats.size.row).forEach {
            tableLayout.addView(makeSeatRow(it, seats))
        }
    }

    private fun makeSeatRow(row: Int, seats: SeatTable): TableRow {
        val tableRow = TableRow(tableLayout.context)
        tableRow.weightSum = seats.size.column.toFloat()
        tableRow.layoutParams = TableLayout.LayoutParams(0, 0, 1f)
        (0 until seats.size.column).forEach {
            tableRow.addView(makeSeatCell(row, it, seats))
        }
        return tableRow
    }

    private fun makeSeatCell(row: Int, column: Int, seats: SeatTable): View {
        return SeatView(tableLayout.context, seats.getSeat(row, column).toView())
    }
}
