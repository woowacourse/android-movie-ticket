package woowacourse.movie.seatSelection

import android.view.Gravity
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import data.SeatPosition
import mapper.toSeatClassModel
import model.SeatModel
import model.SeatSelectionModel
import movie.SeatSelection
import woowacourse.movie.R

class SeatSelectionTable(
    private val view: View,
    private val selectionInfo: SeatSelectionModel,
    private val onSeatClick: () -> Unit,
) {
    private val seatViews = mutableMapOf<SeatPosition, TextView>()

    val seat = SeatSelection()

    val selectedSeat get() = seat.selection
    val isMaxQuantity get() = seat.sizeOfSelection == selectionInfo.Quantity
    val totalPrice get() = seat.getTotalPrice(selectionInfo.reserveTime)

    init {
        createTableLayout()
    }

    fun load(seats: List<SeatModel>) {
        seats.forEach { onSeatClick(it.row, it.column) }
    }

    private fun createTableLayout() {
        val tableLayout = view.findViewById<TableLayout>(R.id.seat_selection_table)
        (0..4).forEach { row -> tableLayout.addView(createTableRows(row)) }
    }

    private fun createTableRows(row: Int): TableRow {
        val tableRow = TableRow(view.context)
        tableRow.gravity = View.TEXT_ALIGNMENT_CENTER
        tableRow.layoutParams = TableLayout.LayoutParams(
            TableLayout.LayoutParams.MATCH_PARENT,
            TableLayout.LayoutParams.MATCH_PARENT,
            1f,
        )
        (0..3).forEach { col -> tableRow.addView(createTextView(row, col)) }
        return tableRow
    }

    private fun createTextView(row: Int, col: Int): TextView {
        val textView = TextView(view.context)
        textView.layoutParams = TableRow.LayoutParams(
            TableRow.LayoutParams.MATCH_PARENT,
            TableRow.LayoutParams.MATCH_PARENT,
            1f,
        )
        textView.text = "%s%d".format('A' + row, col + 1)
        textView.gravity = Gravity.VERTICAL_GRAVITY_MASK + Gravity.CENTER_HORIZONTAL
        textView.setOnClickListener { onSeatClick(row, col) }
        seat.getSeat(SeatPosition(row, col)).seatRank.toSeatClassModel().let {
            textView.textSize = it.size.toFloat()
            textView.setTextColor(it.color)
        }
        seatViews[SeatPosition(row, col)] = textView
        return textView
    }

    private fun onSeatClick(row: Int, col: Int) {
        if (seat[SeatPosition(row, col)].not() && selectionInfo.Quantity <= seat.sizeOfSelection) {
            return
        }
        seat.selectSeat(SeatPosition(row, col))
        onSeatClick()
        drawSeatSelection()
    }

    private fun drawSeatSelection() = seatViews.forEach { (position, view) ->
        when (seat[position]) {
            true -> R.color.seat_selection_seat_selected
            else -> R.color.seat_selection_seat_unselected
        }.let { seatColor ->
            view.setBackgroundResource(seatColor)
        }
    }
}
