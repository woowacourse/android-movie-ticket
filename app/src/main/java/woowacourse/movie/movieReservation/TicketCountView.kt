package woowacourse.movie.movieReservation

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import woowacourse.movie.R

class TicketCountView(
    view: View,
) {
    private val decreaseButton: Button = view.findViewById(R.id.reservation_decrease_ticket_button)
    private val increaseButton: Button = view.findViewById(R.id.reservation_increase_ticket_button)
    private val ticketCountView: TextView = view.findViewById(R.id.reservation_ticket_count)

    init {
        decreaseButton.setOnClickListener {
            val count = ticketCountView.text.toString().toInt() - 1
            ticketCountView.text = count.toString()
        }
        increaseButton.setOnClickListener {
            val count = ticketCountView.text.toString().toInt() + 1
            ticketCountView.text = count.toString()
        }
    }

    fun load(savedInstanceState: Bundle) {
        ticketCountView.text = savedInstanceState.getInt(KEY_COUNT).toString()
    }

    fun save(outState: Bundle) {
        outState.putInt(KEY_COUNT, ticketCountView.text.toString().toInt())
    }

    fun getTicketCount(): Int = ticketCountView.text.toString().toInt()

    companion object {
        private const val KEY_COUNT = "count"
    }
}
