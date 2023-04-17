package woowacourse.movie.activity

import android.os.Bundle
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.Ticket
import woowacourse.movie.domain.movieinfo.Movie
import java.time.format.DateTimeFormatter

class TicketActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)

        val ticket = intent.getSerializableExtra(TICKET_KEY) as Ticket
        val movie = intent.getSerializableExtra(MOVIE_KEY) as Movie

        setUpView(ticket, movie)

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
