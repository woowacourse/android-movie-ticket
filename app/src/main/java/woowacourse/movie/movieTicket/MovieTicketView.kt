package woowacourse.movie.movieTicket

import android.view.View
import android.widget.TextView
import model.MovieTicketModel
import woowacourse.movie.R
import java.text.DecimalFormat
import java.time.format.DateTimeFormatter

class MovieTicketView(
    private val view: View,
) {
    private val titleView: TextView = view.findViewById(R.id.ticket_movie_title)
    private val countView: TextView = view.findViewById(R.id.ticket_total_ticket_count)
    private val releaseDateView: TextView = view.findViewById(R.id.ticket_release_date)
    private val totalPriceView: TextView = view.findViewById(R.id.ticket_total_price)

    fun update(ticket: MovieTicketModel) {
        titleView.text = ticket.title
        countView.text = ticket.reserveTime.format(DATE_FORMATTER)
        releaseDateView.text = view.context.getString(R.string.movie_ticket_count).format(ticket.seats.size, ticket.seats.joinToString(", ") { getSeatString(it.row, it.column) })
        totalPriceView.text = view.context.getString(R.string.movie_ticket_total_price).format(decimalFormat.format(ticket.price))
    }

    private fun getSeatString(row: Int, col: Int): String {
        return "${'A' + row}${1 + col}"
    }
    companion object {
        private val DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        private val decimalFormat = DecimalFormat("#,###")
    }
}
