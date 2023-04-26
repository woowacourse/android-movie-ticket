package woowacourse.movie.view

import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.children
import woowacourse.movie.R
import woowacourse.movie.view.model.SeatUiModel
import woowacourse.movie.view.model.TicketsUiModel

class SeatTable(
    private val tableLayout: TableLayout,
    val rowSize: Int = DEFAULT_ROW_SIZE,
    val colSize: Int = DEFAULT_COL_SIZE,
    private val onClick: (SeatView) -> Unit
) {
    private val seatViews: MutableList<SeatView> = mutableListOf()

    init {
        makeSeatTable()
    }

    private fun makeSeatTable() {
        repeat(rowSize) { row ->
            val tableRow = makeTableRow()
            repeat(colSize) { col ->
                val seatView = SeatView(
                    TextView(tableLayout.context), SeatUiModel.toChar(row), col + 1, onClick
                )
                seatViews.add(seatView)
                tableRow.addView(seatView.view)
            }
            tableLayout.addView(tableRow)
        }
    }

    fun updateTable(ticketsUiModel: TicketsUiModel) {
        initTableBackground()
        ticketsUiModel.list.forEach { ticketUiModel ->
            val index = convertTablePositionToIndex(ticketUiModel.seat)
            seatViews[index].setBackgroundColorId(R.color.seat_selection_selected_seat_color)
        }
    }

    fun initTableBackground() {
        seatViews.forEach { it.setBackgroundColorId(R.color.white) }
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

    private fun convertTablePositionToIndex(seatUiModel: SeatUiModel): Int {
        return (SeatUiModel.toNumber(seatUiModel.row) - 2) * (colSize) + (seatUiModel.col - 1)
    }

    companion object {
        private const val DEFAULT_ROW_SIZE = 5
        private const val DEFAULT_COL_SIZE = 4
        private const val TABLE_WEIGHT = 1f
    }
}
