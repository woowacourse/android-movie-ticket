package woowacourse.movie.movieReservation

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import woowacourse.movie.R

class ReservationTicketCount(
    view: View,
) {
    private val decreaseButton: Button = view.findViewById(R.id.reservation_decrease_ticket_button)
    private val increaseButton: Button = view.findViewById(R.id.reservation_increase_ticket_button)
    private val ticketCountView: TextView = view.findViewById(R.id.reservation_ticket_count)

    val count: Int
        get() = ticketCountView.text.toString().toInt()

    init {
        decreaseButton.setOnClickListener {
            val count = count - 1
            ticketCountView.text = count.toString()
        }
        increaseButton.setOnClickListener {
            val count = count + 1
            ticketCountView.text = count.toString()
        }
    }

    fun load(savedInstanceState: Bundle) {
        ticketCountView.text = savedInstanceState.getInt(KEY_COUNT).toString()
    }

    fun save(outState: Bundle) {
        outState.putInt(KEY_COUNT, count)
    }

    companion object {
        private const val KEY_COUNT = "count"
    }
}
