package woowacourse.movie.view

import android.os.Bundle
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.Ticket
import woowacourse.movie.domain.movieinfo.Movie
import woowacourse.movie.view.viewmodel.MovieUIModel
import woowacourse.movie.view.viewmodel.TicketUIModel
import woowacourse.movie.view.viewmodel.toMovie
import woowacourse.movie.view.viewmodel.toTicket
import java.time.format.DateTimeFormatter

class TicketActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)

        val ticketUI = intent.getSerializableExtra(TICKET_KEY) as TicketUIModel
        val movieUI = intent.getSerializableExtra(MOVIE_KEY) as MovieUIModel

        setUpView(ticketUI.toTicket(), movieUI.toMovie())

        setBackToBefore(R.id.ticket_toolbar)
    }

    private fun setUpView(ticket: Ticket, movie: Movie) {
        val movieTitle = findViewById<TextView>(R.id.ticket_title)
        val movieDate = findViewById<TextView>(R.id.ticket_date)
        val numberOfPeople = findViewById<TextView>(R.id.ticket_numberOfPeople)
        val price = findViewById<TextView>(R.id.ticket_price)

        movieTitle.text = movie.title
        movieDate.text = ticket.date.format(DateTimeFormatter.ofPattern("yyyy.M.d HH:mm"))
        numberOfPeople.text =
            this.getString(R.string.ticket_number_of_people, ticket.numberOfPeople)
        price.text = this.getString(R.string.ticket_price, ticket.calculateTotalPrice())
    }

    companion object {
        private const val TICKET_KEY = "ticket"
        private const val MOVIE_KEY = "movie"
    }
}
