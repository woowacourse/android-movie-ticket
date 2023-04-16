package woowacourse.movie.movieTicket

import android.view.View
import android.widget.TextView
import entity.MovieTicket
import woowacourse.movie.R
import java.text.DecimalFormat

class MovieTicketView(
    private val view: View,
) {
    private val titleView: TextView = view.findViewById(R.id.ticket_movie_title)
    private val countView: TextView = view.findViewById(R.id.ticket_total_ticket_count)
    private val releaseDateView: TextView = view.findViewById(R.id.ticket_release_date)
    private val totalPriceView: TextView = view.findViewById(R.id.ticket_total_price)

    fun update(ticket: MovieTicket) {
        titleView.text = ticket.title
        countView.text = ticket.getReserveDate()
        releaseDateView.text = view.context.getString(R.string.movie_ticket_count).format(ticket.size)
        totalPriceView.text = view.context.getString(R.string.movie_ticket_total_price)
            .format(decimalFormat.format(ticket.getTotalPrice()))
    }

    companion object {
        private val decimalFormat = DecimalFormat("#,###")
    }
}
