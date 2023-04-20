package woowacourse.movie.seatselection

import android.graphics.Color
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.children
import domain.reservation.SeatReservation
import domain.seat.ScreeningSeat
import domain.seat.SeatState
import woowacourse.movie.R
import woowacourse.movie.util.toScreeningSeat

class ScreeningSeatViewSetter(
    private val seatTable: TableLayout,
    private val seatReservation: SeatReservation
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

            when (seatReservation.screeningSeats.values[seat]) {
                SeatState.SELECTED -> seatView.markSelected()
                else -> seatView.markAvailable()
            }
        }
    }

    fun setSeatViewClickedListener(updatePaymentAmountView: (paymentAmount: Int) -> Unit) {
        seatTableConfiguration.forEachIndexed { seatPosition, seatView ->
            seatView.setOnClickListener {
                val seat = seatPosition.toScreeningSeat()
                val clickedSeatState = seatReservation.screeningSeats.values[seat]

                clickedSeatState?.let {
                    runCatching {
                        onSeatViewClicked(seatView, seat)
                    }.onFailure {
                        Toast.makeText(seatTable.context, it.message, Toast.LENGTH_SHORT).show()
                    }
                }
                updatePaymentAmountView(seatReservation.getTotalPaymentAmount().value)
            }
        }
    }

    private fun onSeatViewClicked(seatView: TextView, seat: ScreeningSeat) {
        if (seatReservation.screeningSeats.values[seat] == SeatState.SELECTED) {
            seatReservation.cancelSeat(seat)
            seatView.markAvailable()
        } else {
            seatReservation.selectSeat(seat)
            seatView.markSelected()
        }
    }

    private fun TextView.markSelected() = setBackgroundColor(Color.YELLOW)

    private fun TextView.markAvailable() = setBackgroundColor(Color.WHITE)
}
