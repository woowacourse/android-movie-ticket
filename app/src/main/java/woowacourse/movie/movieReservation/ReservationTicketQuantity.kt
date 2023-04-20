package woowacourse.movie.movieReservation

import android.os.Bundle
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

    fun load(savedInstanceState: Bundle?) {
        ticketQuantityTextView.text = savedInstanceState?.getInt(KEY_COUNT).toString()
    }

    fun save(outState: Bundle) {
        outState.putInt(KEY_COUNT, quantity.toInt())
    }

    companion object {
        private const val KEY_COUNT = "count"
    }
}
