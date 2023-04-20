package woowacourse.movie.seatSelection

import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.children
import data.SeatPosition
import model.MovieTicketModel
import model.SeatSelectionModel
import movie.SeatSelection
import movie.TicketQuantity
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
                seatSelection.selectSeat(SeatPosition(row, col), TicketQuantity(selectionInfo.Quantity))
                drawSeatSelection()
                updateInfo()
            }
        }

        movieTitle.text = selectionInfo.title

        seatSelectionConfirm.setOnClickListener {
            onConfirmClick(MovieTicketModel(selectionInfo, seatSelection))
        }

        updateInfo()
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
        private const val TOTAL_PRICE = "%d원"
    }
}
