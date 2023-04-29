package woowacourse.movie.seatSelection

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import data.SeatPosition
import mapper.toSeatModel
import model.MovieTicketModel
import model.SeatModel
import model.SeatSelectionModel
import movie.SeatSelection
import woowacourse.movie.R
import java.io.Serializable

class SeatSelectionTable(
    private val view: View,
    private val selectionInfo: SeatSelectionModel,
    private val onConfirmClick: (MovieTicketModel) -> Unit,
) {
    private val movieTitle = view.findViewById<TextView>(R.id.seat_selection_title)
    private val ticketTotalPrice = view.findViewById<TextView>(R.id.seat_selection_price)
    private val seatSelectionConfirm = view.findViewById<TextView>(R.id.seat_selection_confirm)
    private val seatSelection = SeatSelection()

    private val seatViews = mutableMapOf<SeatPosition, TextView>()

    init {
        movieTitle.text = selectionInfo.title

        seatSelectionConfirm.setOnClickListener {
            onConfirmClick(MovieTicketModel(selectionInfo, seatSelection))
        }

        createTableLayout()
        updateInfo()
    }
    private fun createTableLayout() {
        val tableLayout = view.findViewById<TableLayout>(R.id.seat_selection_table)
        (0..4).forEach { row -> tableLayout.addView(createTableRows(row)) }
    }

    private fun createTableRows(row: Int): TableRow {
        val tableRow = TableRow(view.context)
        tableRow.gravity = View.TEXT_ALIGNMENT_CENTER
        tableRow.layoutParams = TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.MATCH_PARENT, 1f)
        (0..3).forEach { col -> tableRow.addView(createTextView(row, col)) }
        return tableRow
    }

    private fun createTextView(row: Int, col: Int): TextView {
        val textView = TextView(view.context)
        textView.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.MATCH_PARENT, 1f)
        textView.text = "%s%d".format('A' + row, col + 1)
        textView.gravity = Gravity.VERTICAL_GRAVITY_MASK + Gravity.CENTER_HORIZONTAL
        textView.textSize = 20f
        textView.setOnClickListener {
            onSeatClick(row, col)
        }
        seatViews[SeatPosition(row, col)] = textView
        return textView
    }

    private fun onSeatClick(row: Int, col: Int) {
        if (seatSelection[SeatPosition(row, col)].not() && selectionInfo.Quantity <= seatSelection.sizeOfSelection) {
            return
        }
        seatSelection.selectSeat(SeatPosition(row, col))
        drawSeatSelection()
        updateInfo()
    }

    fun loadSeatSelection(savedInstanceState: Bundle) {
        (savedInstanceState.getSerializable(KEY_SEAT_SELECTION_SEATS) as ArrayList<*>)
            .filterIsInstance<SeatModel>()
            .forEach { onSeatClick(it.row, it.column) }
    }

    fun saveInstanceState(outState: Bundle) {
        val seatModels = seatSelection.selection.map { it.toSeatModel() }
        outState.putSerializable(KEY_SEAT_SELECTION_SEATS, seatModels as Serializable)
    }

    private fun updateInfo() {
        ticketTotalPrice.text = TOTAL_PRICE.format(seatSelection.getTotalPrice(selectionInfo.reserveTime))
        seatSelectionConfirm.isEnabled = seatSelection.sizeOfSelection == selectionInfo.Quantity
    }

    private fun drawSeatSelection() = seatViews.forEach { (position, view) ->
        when (seatSelection[position]) {
            true -> R.color.seat_selection_seat_selected
            else -> R.color.seat_selection_seat_unselected
        }.let { seatColor ->
            view.setBackgroundResource(seatColor)
        }
    }

    companion object {
        private const val KEY_SEAT_SELECTION_SEATS = "seatSelectionSeats"
        private const val TOTAL_PRICE = "%d원"
    }
}
