package woowacourse.movie.movieTicket

import android.content.Context
import android.widget.TextView
import entity.MovieTicket
import woowacourse.movie.R
import java.text.DecimalFormat

class MovieTicketView(
    private val titleView: TextView,
    private val countView: TextView,
    private val releaseDateView: TextView,
    private val totalPriceView: TextView,
) {
    fun bind(context: Context, ticket: MovieTicket) {
        titleView.text = ticket.title
        countView.text = ticket.getReserveDate()
        releaseDateView.text = context.getString(R.string.movie_ticket_count).format(ticket.size)
        totalPriceView.text = context.getString(R.string.movie_ticket_total_price)
            .format(decimalFormat.format(ticket.getTotalPrice()))
    }

    companion object {
        private val decimalFormat = DecimalFormat("#,###")
    }
}
