package woowacourse.movie.activity.ticketresult

import android.view.ViewGroup
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.model.TicketModel
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class TicketView(private val viewGroup: ViewGroup) {
    fun set(ticket: TicketModel) {
        initTitle(ticket.title)
        initPlayingDate(ticket.playingDate, ticket.playingTime)
        initCount(ticket.count)
        initPricePayment(ticket.price)
    }

    private fun initTitle(title: String) {
        viewGroup.findViewById<TextView>(R.id.text_title).text = title
    }

    private fun initPlayingDate(playingDate: LocalDate, playingTime: LocalTime) {
        viewGroup.findViewById<TextView>(R.id.text_playing_date).text = viewGroup.context.getString(
            R.string.date_time,
            DateTimeFormatter.ofPattern(viewGroup.context.getString(R.string.date_format)).format(playingDate),
            DateTimeFormatter.ofPattern(viewGroup.context.getString(R.string.time_format)).format(playingTime)
        )
    }

    private fun initCount(count: Int) {
        viewGroup.findViewById<TextView>(R.id.text_person_count).text = viewGroup.context.getString(R.string.normal_count, count)
    }

    private fun initPricePayment(price: Int) {
        viewGroup.findViewById<TextView>(R.id.text_price_payment).text = viewGroup.context.getString(
            R.string.price_payment,
            DecimalFormat(viewGroup.context.getString(R.string.decimal_format)).format(price)
        )
    }
}
