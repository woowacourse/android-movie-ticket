package woowacourse.movie.seatSelection

import android.os.Bundle
import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.children
import data.SeatPosition
import mapper.toSeatModel
import model.MovieTicketModel
import model.SeatModel
import model.SeatSelectionModel
import movie.SeatSelection
import woowacourse.movie.R

class SeatSelectionTable(
    private val view: View,
    private val selectionInfo: SeatSelectionModel,
    private val onConfirmClick: (MovieTicketModel) -> Unit,
) {
    private val movieTitle by lazy { view.findViewById<TextView>(R.id.seat_selection_title) }
    private val ticketTotalPrice by lazy { view.findViewById<TextView>(R.id.seat_selection_price) }
    private val seatSelectionConfirm by lazy { view.findViewById<TextView>(R.id.seat_selection_confirm) }
    private val seatSelection = SeatSelection()

    private val seatSelectionTableBoxes by lazy {
        view.findViewById<TableLayout>(R.id.seat_selection_table).children.filterIsInstance<TableRow>()
            .flatMapIndexed { rowIndex, row ->
                row.children.filterIsInstance<TextView>().mapIndexed { colIndex, item ->
                    Triple(rowIndex, colIndex, item)
                }
            }
    }

    init {
        seatSelectionTableBoxes.forEach { (row, col, item) ->
            item.setOnClickListener {
                onSeatClick(row, col)
            }
        }

        movieTitle.text = selectionInfo.title

        seatSelectionConfirm.setOnClickListener {
            onConfirmClick(MovieTicketModel(selectionInfo, seatSelection))
        }

        updateInfo()
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
        savedInstanceState.getParcelableArray(KEY_SEAT_SELECTION_SEATS)?.map { it as SeatModel }
            ?.forEach { onSeatClick(it.row, it.column) }
    }

    fun saveInstanceState(outState: Bundle) {
        val seatModels = seatSelection.selection.map { it.toSeatModel() }
        outState.putParcelableArray(
            KEY_SEAT_SELECTION_SEATS,
            seatModels.toTypedArray(),
        )
    }

    private fun updateInfo() {
        ticketTotalPrice.text = TOTAL_PRICE.format(seatSelection.getTotalPrice(selectionInfo.reserveTime))
        seatSelectionConfirm.isEnabled = seatSelection.sizeOfSelection == selectionInfo.Quantity
    }

    private fun drawSeatSelection() = seatSelectionTableBoxes.forEach { (row, col, box) ->
        when (seatSelection[SeatPosition(row, col)]) {
            true -> R.color.seat_selection_seat_selected
            else -> R.color.seat_selection_seat_unselected
        }.let { seatColor ->
            box.setBackgroundResource(seatColor)
        }
    }

    companion object {
        private const val KEY_SEAT_SELECTION_SEATS = "seatSelectionSeats"
        private const val TOTAL_PRICE = "%d원"
    }
}
