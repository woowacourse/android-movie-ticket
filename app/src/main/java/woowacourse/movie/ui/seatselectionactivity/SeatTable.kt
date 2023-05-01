package woowacourse.movie.ui.seatselectionactivity

import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView

class SeatTable(
    private val tableLayout: TableLayout,
    private val row: Int,
    private val col: Int,
    private val onClick: (SeatView) -> Unit
) {

    fun setView() {
        repeat(row) { row ->
            val tableRow = makeTableRow(row, col)
            tableLayout.addView(tableRow)
        }
    }

    private fun makeTableRow(row: Int, col: Int): TableRow {
        val tableRow = TableRow(tableLayout.context)
        repeat(col) { column ->
            tableRow.layoutParams = TableLayout.LayoutParams(
                TableLayout.LayoutParams.MATCH_PARENT,
                TableLayout.LayoutParams.MATCH_PARENT,
                TABLE_WEIGHT
            )
            val seatView = SeatView(
                TextView(tableLayout.context), row, column + 1, onClick
            )
            tableRow.addView(seatView.view)
        }
        return tableRow
    }

    companion object {
        private const val TABLE_WEIGHT = 1f
    }
}