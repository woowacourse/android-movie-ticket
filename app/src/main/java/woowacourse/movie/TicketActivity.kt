package woowacourse.movie

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import woowacourse.movie.domain.Ticket
import woowacourse.movie.domain.TicketPrice
import woowacourse.movie.domain.movieinfo.Movie
import woowacourse.movie.domain.movieinfo.MovieDate
import woowacourse.movie.domain.movieinfo.MovieTime
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class TicketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)
        setToolbar()

        val ticket = intent.getSerializableExtra(TICKET_KEY) as Ticket
        val movie = intent.getSerializableExtra(MOVIE_KEY) as Movie
        val date = intent.getSerializableExtra(DATE_KEY) as MovieDate
        val time = intent.getSerializableExtra(TIME_KEY) as MovieTime

        showTicketInfo(movie, date.date, time.time)
        showTicketCount(ticket)
        showTicketPrice(date.date, time.time, ticket.numberOfPeople)
    }

    private fun setToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.ticket_toolbar)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun formatMovieDateTime(date: LocalDate, time: LocalTime): String {
        val formatDate = date.format(DateTimeFormatter.ofPattern(getString(R.string.date_format)))
        val formatTime = time.format(DateTimeFormatter.ofPattern(getString(R.string.time_format)))

        return formatDate.plus(" $formatTime")
    }

    private fun showTicketInfo(movie: Movie, date: LocalDate, time: LocalTime) {
        val movieTitle = findViewById<TextView>(R.id.ticket_title)
        val movieDate = findViewById<TextView>(R.id.ticket_date)
        movieTitle.text = movie.title
        movieDate.text = formatMovieDateTime(date, time)
    }

    private fun showTicketCount(ticket: Ticket) {
        val numberOfPeople = findViewById<TextView>(R.id.number_of_people)
        numberOfPeople.text = getString(R.string.ticket_number, ticket.numberOfPeople)
    }

    private fun showTicketPrice(date: LocalDate, time: LocalTime, count: Int) {
        val price = findViewById<TextView>(R.id.ticket_price)
        val ticketPrice = TicketPrice()
        val totalTicketPrice = ticketPrice.applyPolicy(LocalDateTime.of(date, time)) * count

        price.text = getString(R.string.ticket_price, totalTicketPrice.price)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val TICKET_KEY = "ticket"
        private const val MOVIE_KEY = "movie"
        private const val DATE_KEY = "movie_date"
        private const val TIME_KEY = "movie_time"
    }
}
