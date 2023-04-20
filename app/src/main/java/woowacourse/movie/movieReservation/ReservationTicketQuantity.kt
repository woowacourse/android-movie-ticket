package woowacourse.movie.movieReservation

import android.view.View
import android.widget.Button
import android.widget.TextView
import movie.TicketQuantity
import woowacourse.movie.R

class ReservationTicketQuantity(
    view: View,
) {
    private val decreaseButton: Button = view.findViewById(R.id.reservation_decrease_ticket_button)
    private val increaseButton: Button = view.findViewById(R.id.reservation_increase_ticket_button)
    private val ticketQuantityTextView: TextView = view.findViewById(R.id.reservation_ticket_count)

    val quantity: TicketQuantity
        get() = TicketQuantity(ticketQuantityTextView.text.toString().toInt())

    init {
        decreaseButton.setOnClickListener {
            val count = quantity.dec()
            ticketQuantityTextView.text = count.toString()
        }
        increaseButton.setOnClickListener {
            val count = quantity.inc()
            ticketQuantityTextView.text = count.toString()
        }
    }

    fun initTicketQuantity(ticketQuantity: TicketQuantity) {
        ticketQuantityTextView.text = ticketQuantity.toString()
    }
}
