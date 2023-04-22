package woowacourse.movie.activity.ticketresult

import android.view.ViewGroup
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.SeatModel
import woowacourse.movie.model.TicketModel
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TicketView(private val viewGroup: ViewGroup) {
    fun set(ticket: TicketModel) {
        initTitle(ticket.title)
        initPlayingDate(ticket.playingDateTime)
        initCountSeats(ticket.count, ticket.seats)
        initPricePayment(ticket.price.price)
    }

    private fun initTitle(title: String) {
        viewGroup.findViewById<TextView>(R.id.text_title).text = title
    }

    private fun initPlayingDate(playingDateTime: LocalDateTime) {
        viewGroup.findViewById<TextView>(R.id.text_playing_date).text = viewGroup.context.getString(
            R.string.date_time,
            DateTimeFormatter.ofPattern(viewGroup.context.getString(R.string.date_format))
                .format(playingDateTime),
            DateTimeFormatter.ofPattern(viewGroup.context.getString(R.string.time_format))
                .format(playingDateTime),
        )
    }

    private fun initCountSeats(count: Int, seats: List<SeatModel>) {
        val convertSeats = seats.joinToString(separator = ", ", transform = { it.seatId })
        viewGroup.findViewById<TextView>(R.id.text_person_count_seats).text =
            viewGroup.context.getString(R.string.normal_count_seat, count, convertSeats)
    }

    private fun initPricePayment(price: String) {
        viewGroup.findViewById<TextView>(R.id.text_price_payment).text =
            viewGroup.context.getString(
                R.string.price_payment,
                price,
            )
    }
}
