package woowacourse.movie.view

import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import woowacourse.movie.view.model.SeatUiModel

class SeatTable(
    private val tableLayout: TableLayout,
    private val rowSize: Int = DEFAULT_ROW_SIZE,
    private val colSize: Int = DEFAULT_COL_SIZE,
    private val onClick: (SeatView) -> Unit
) {
    fun makeSeatTable() {
        repeat(rowSize) { row ->
            val tableRow = makeTableRow()
            repeat(colSize) { col ->
                val seatView = SeatView(
                    TextView(tableLayout.context), SeatUiModel.toChar(row), col + 1, onClick
                )
                tableRow.addView(seatView.view)
            }
            tableLayout.addView(tableRow)
        }
    }

    private fun makeTableRow(): TableRow {
        val tableRow = TableRow(tableLayout.context)
        tableRow.layoutParams = TableLayout.LayoutParams(
            TableLayout.LayoutParams.MATCH_PARENT,
            TableLayout.LayoutParams.MATCH_PARENT,
            TABLE_WEIGHT
        )
        return tableRow
    }

    companion object {
        private const val DEFAULT_ROW_SIZE = 5
        private const val DEFAULT_COL_SIZE = 4
        private const val TABLE_WEIGHT = 1f
    }
}
