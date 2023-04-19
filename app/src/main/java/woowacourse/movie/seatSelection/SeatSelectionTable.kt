package woowacourse.movie.seatSelection

import android.view.View
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.core.view.children
import data.SeatPosition
import mapper.toSeatModel
import model.SeatSelectionModel
import model.TicketModel
import movie.SeatSelection
import movie.pricePolicy.NormalPricePolicy
import woowacourse.movie.R

class SeatSelectionTable(
    private val view: View,
    private val selectionInfo: SeatSelectionModel,
    private val onConfirmClicked: (TicketModel) -> Unit,
) {
    private val seatSelectionTableBoxes by lazy {
        view.findViewById<TableLayout>(R.id.seat_selection_table).children.filterIsInstance<TableRow>()
            .map { it.children.filterIsInstance<TextView>() }
    }

    private val movieTitle by lazy { view.findViewById<TextView>(R.id.seat_selection_title) }
    private val ticketTotalPrice by lazy { view.findViewById<TextView>(R.id.seat_selection_price) }
    private val seatSelectionConfirm by lazy { view.findViewById<TextView>(R.id.seat_selection_confirm) }
    private val seatSelection = SeatSelection(5, 4)

    private val myMap = mutableMapOf<SeatPosition, TextView>()
    init {
        seatSelectionTableBoxes.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { columnIndex, box ->
                myMap[SeatPosition(rowIndex, columnIndex)] = box
            }
        }

        movieTitle.text = selectionInfo.title
        ticketTotalPrice.text = TOTAL_PRICE.format(seatSelection.getSelectedSeatsPrice(selectionInfo.reserveTime, NormalPricePolicy()))
        createSeatSelection()
        registerSeatSelectionListener()
        seatSelectionConfirm.setOnClickListener {
            val confirmedTicket = TicketModel(
                selectionInfo.title,
                selectionInfo.reserveTime,
                seatSelection.getSelectedSeatsPrice(selectionInfo.reserveTime, NormalPricePolicy()),
                seatSelection.getSelectedSeats().map { it.toSeatModel() },
            )
            onConfirmClicked(confirmedTicket)
        }
    }

    private fun createSeatSelection() = seatSelectionTableBoxes.map { row -> row.map { box -> box } }

    private fun registerSeatSelectionListener() = myMap.forEach { (position, box) ->
        box.setOnClickListener {
            if (seatSelection.isAvailableByPosition(position).not() && seatSelection.getSelectedSeats().count() >= selectionInfo.peopleNumber) {
                return@setOnClickListener
            }

            seatSelection.clickSeat(seatSelection.findByPosition(position))
            drawSeatSelection()
            ticketTotalPrice.text = TOTAL_PRICE.format(seatSelection.getSelectedSeatsPrice(selectionInfo.reserveTime, NormalPricePolicy()))
            seatSelectionConfirm.isEnabled = seatSelection.getSelectedSeats().count() == selectionInfo.peopleNumber
        }
    }

    private fun drawSeatSelection() = myMap.forEach { (position, box) ->
        when (seatSelection.isAvailableByPosition(position)) {
            true -> box.setBackgroundResource(R.color.seat_selection_seat_selected)
            else -> box.setBackgroundResource(R.color.seat_selection_seat_unselected)
        }
    }

    companion object {
        private const val TOTAL_PRICE = "%d원"
    }
}
