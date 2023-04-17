package woowacourse.movie

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import woowacourse.movie.domain.policy.MovieDayDiscountPolicy
import woowacourse.movie.domain.policy.TimeDiscountPolicy
import woowacourse.movie.dto.MovieDateDto
import woowacourse.movie.dto.MovieDto
import woowacourse.movie.dto.MovieTimeDto
import woowacourse.movie.dto.TicketCountDto
import woowacourse.movie.dto.TicketPriceDto
import woowacourse.movie.mapper.mapToTicketPrice
import woowacourse.movie.mapper.mapToTicketPriceDto
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class TicketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)
        setToolbar()

        val ticket = intent.getSerializableExtra(TICKET_KEY) as TicketCountDto
        val movie = intent.getSerializableExtra(MOVIE_KEY) as MovieDto
        val date = intent.getSerializableExtra(DATE_KEY) as MovieDateDto
        val time = intent.getSerializableExtra(TIME_KEY) as MovieTimeDto

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

    private fun showTicketInfo(movie: MovieDto, date: LocalDate, time: LocalTime) {
        val movieTitle = findViewById<TextView>(R.id.ticket_title)
        val movieDate = findViewById<TextView>(R.id.ticket_date)
        movieTitle.text = movie.title
        movieDate.text = formatMovieDateTime(date, time)
    }

    private fun showTicketCount(ticket: TicketCountDto) {
        val numberOfPeople = findViewById<TextView>(R.id.number_of_people)
        numberOfPeople.text = getString(R.string.ticket_number, ticket.numberOfPeople)
    }

    private fun showTicketPrice(date: LocalDate, time: LocalTime, count: Int) {
        val price = findViewById<TextView>(R.id.ticket_price)
        val ticketPrice = TicketPriceDto(
            listOf(
                MovieDayDiscountPolicy(),
                TimeDiscountPolicy(),
            ),
        )
        val totalTicketPrice =
            ticketPrice.mapToTicketPrice().applyPolicy(
                LocalDateTime.of(date, time),
            ) * count

        price.text = getString(R.string.ticket_price, totalTicketPrice.mapToTicketPriceDto().price)
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
