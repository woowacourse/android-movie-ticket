package woowacourse.movie.seatselection

import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.children
import domain.reservation.SeatSelection
import domain.seat.ScreeningSeat
import domain.seat.SeatState
import woowacourse.movie.R

class ScreeningSeatViewSetter(
    private val seatTable: TableLayout,
    private val seatSelection: SeatSelection
) {

    private val seatTableConfiguration = seatTable.findViewById<TableLayout>(R.id.seat_table_layout)
        .children
        .filterIsInstance<TableRow>()
        .flatMap { it.children }
        .filterIsInstance<TextView>()
        .toList()

    init {
        setSeatView()
    }

    private fun setSeatView() {
        seatTableConfiguration.forEachIndexed { seatPosition, seatView ->
            val seat = seatPosition.toScreeningSeat()

            when (seatSelection[seat]) {
                SeatState.SELECTED -> seatView.isSelected = true
                else -> seatView.isSelected = false
            }
        }
    }

    fun setSeatViewClickedListener(
        updatePaymentAmountView: (paymentAmount: Int) -> Unit,
        updateButtonState: (isCompleted: Boolean) -> Unit
    ) {
        seatTableConfiguration.forEachIndexed { seatPosition, seatView ->
            seatView.setOnClickListener {
                val seat = seatPosition.toScreeningSeat()

                seatSelection[seat].apply {
                    runCatching {
                        onSeatViewClicked(seatView, seat)
                    }.onFailure {
                        Toast.makeText(seatTable.context, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
                updatePaymentAmountView(seatSelection.getTotalPaymentAmount().value)
                updateButtonState(seatSelection.isCompleted)
            }
        }
    }

    private fun onSeatViewClicked(seatView: TextView, seat: ScreeningSeat) {
        if (seatSelection[seat] == SeatState.SELECTED) {
            seatSelection.cancelSeat(seat)
            seatView.isSelected = false
        } else {
            seatSelection.selectSeat(seat)
            seatView.isSelected = true
        }
    }
}
