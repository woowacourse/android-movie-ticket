package woowacourse.movie

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Ticket
import java.time.format.DateTimeFormatter

class TicketActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket)

        val ticket = intent.getSerializableExtra(TICKET_KEY) as Ticket
        val movie = intent.getSerializableExtra(MOVIE_KEY) as Movie

        val movieTitle = findViewById<TextView>(R.id.ticket_title)
        val movieDate = findViewById<TextView>(R.id.ticket_date)
        val numberOfPeople = findViewById<TextView>(R.id.ticket_numberOfPeople)
        val price = findViewById<TextView>(R.id.ticket_price)
        val toolbar = findViewById<Toolbar>(R.id.ticket_toolbar)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        movieTitle.text = movie.title
        movieDate.text = ticket.date.format(DateTimeFormatter.ofPattern("yyyy.M.d HH:mm"))
        numberOfPeople.text = ticket.numberOfPeople.toString()
        price.text = ticket.calculateTotalPrice().toString()
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
    }
}
