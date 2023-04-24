package woowacourse.movie.seatselection

import android.view.View
import android.widget.Button
import android.widget.TextView
import domain.reservation.SeatSelection
import woowacourse.movie.R

class ScreeningSeatNavigationView(view: View) {

    private val paymentAmountText = view.findViewById<TextView>(R.id.seat_selection_payment_amount_text)
    private val completeButton = view.findViewById<Button>(R.id.seat_selection_complete_button)

    fun bind(seatSelection: SeatSelection) {
        paymentAmountText.text = seatSelection.getTotalPaymentAmount().value.toString()
        completeButton.isEnabled = seatSelection.isCompleted
    }
}
