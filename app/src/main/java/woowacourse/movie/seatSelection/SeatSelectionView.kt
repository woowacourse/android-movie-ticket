package woowacourse.movie.seatSelection

import android.os.Bundle
import android.view.View
import android.widget.TextView
import mapper.toSeatModel
import model.MovieTicketModel
import model.SeatModel
import model.SeatSelectionModel
import woowacourse.movie.R
import woowacourse.movie.utils.getSerializableCompat
import java.io.Serializable

class SeatSelectionView(
    view: View,
    private val selectionInfo: SeatSelectionModel,
    private val onConfirmClick: (MovieTicketModel) -> Unit,
) {
    private val movieTitle = view.findViewById<TextView>(R.id.seat_selection_title)
    private val ticketTotalPrice = view.findViewById<TextView>(R.id.seat_selection_price)
    private val seatSelectionConfirm = view.findViewById<TextView>(R.id.seat_selection_confirm)
    private val table = SeatSelectionTable(view, selectionInfo, ::updateInfo)

    init {
        movieTitle.text = selectionInfo.title

        seatSelectionConfirm.setOnClickListener {
            onConfirmClick(MovieTicketModel(selectionInfo, table.seat))
        }

        updateInfo()
    }

    fun restoreInstanceState(savedInstanceState: Bundle) {
        (savedInstanceState.getSerializableCompat(KEY_SEAT_SELECTION_SEATS) as ArrayList<*>?)
            ?.filterIsInstance<SeatModel>()
            ?.let { table.load(it) }
    }

    fun saveInstanceState(outState: Bundle) {
        val seatModels = table.selectedSeat.map { it.toSeatModel() }
        outState.putSerializable(KEY_SEAT_SELECTION_SEATS, seatModels as Serializable)
    }

    private fun updateInfo() {
        ticketTotalPrice.text = TOTAL_PRICE.format(table.totalPrice)
        seatSelectionConfirm.isEnabled = table.isMaxQuantity
    }

    companion object {
        private const val KEY_SEAT_SELECTION_SEATS = "seatSelectionSeats"
        private const val TOTAL_PRICE = "%d원"
    }
}
