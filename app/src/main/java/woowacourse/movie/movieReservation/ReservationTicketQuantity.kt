package woowacourse.movie.movieReservation

import android.view.View
import android.widget.Button
import android.widget.TextView
import woowacourse.movie.R

class ReservationTicketQuantity(
    view: View,
) {
    private val decreaseButton: Button = view.findViewById(R.id.reservation_decrease_ticket_button)
    private val increaseButton: Button = view.findViewById(R.id.reservation_increase_ticket_button)
    private val ticketQuantityTextView: TextView = view.findViewById(R.id.reservation_ticket_count)

    var quantity: Int = 1
        set(value) {
            field = value
            if (field < 1) field = 1
            ticketQuantityTextView.text = field.toString()
        }

    init {
        decreaseButton.setOnClickListener {
            quantity -= 1
        }
        increaseButton.setOnClickListener {
            quantity += 1
        }
    }
}
