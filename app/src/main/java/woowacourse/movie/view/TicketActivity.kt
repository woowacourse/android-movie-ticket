package woowacourse.movie.view

import android.os.Bundle
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.view.viewmodel.MovieUIModel
import woowacourse.movie.view.viewmodel.TicketUIModel
import java.time.format.DateTimeFormatter

class TicketActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)
        setBackToBefore()

        val ticketUI = intent.getSerializableExtra(TICKET_KEY) as TicketUIModel
        val movieUI = intent.getSerializableExtra(MOVIE_KEY) as MovieUIModel

        setUpView(ticketUI, movieUI)
    }

    private fun setUpView(ticket: TicketUIModel, movie: MovieUIModel) {
        val movieTitle = findViewById<TextView>(R.id.ticket_title)
        val movieDate = findViewById<TextView>(R.id.ticket_date)
        val numberOfPeople = findViewById<TextView>(R.id.ticket_numberOfPeople)
        val price = findViewById<TextView>(R.id.ticket_price)

        movieTitle.text = movie.title
        movieDate.text = ticket.date.format(DateTimeFormatter.ofPattern(FORMATTER))
        numberOfPeople.text =
            getString(
                R.string.ticket_number_of_people_seats,
                ticket.numberOfPeople,
                ticket.getSeats()
            )
        price.text = getString(R.string.ticket_price, ticket.price)
    }

    companion object {
        private const val TICKET_KEY = "ticket"
        private const val MOVIE_KEY = "movie"
        private const val FORMATTER = "yyyy.M.d HH:mm"
    }
}
