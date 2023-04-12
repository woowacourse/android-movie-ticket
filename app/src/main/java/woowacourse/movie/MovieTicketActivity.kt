package woowacourse.movie

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import java.text.DecimalFormat
import java.time.format.DateTimeFormatter

class MovieTicketActivity : AppCompatActivity() {
    private val movieInfo by lazy { intent.getSerializableExtra("movieInfo") as MovieInfo }
    private val ticket by lazy { intent.getSerializableExtra("movieTicket") as MovieTicket }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_ticket)
        updateMovieView()
        registerToolbar()
    }

    private fun registerToolbar() {
        val reservationToolbar = findViewById<Toolbar>(R.id.ticket_toolbar)
        setSupportActionBar(reservationToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
            else -> {}
        }
        return super.onOptionsItemSelected(item)
    }

    private fun updateMovieView() {
        val ticketTitleView = findViewById<TextView>(R.id.ticket_movie_title)
        val ticketCountView = findViewById<TextView>(R.id.ticket_total_ticket_count)
        val ticketMovieReleaseDateView = findViewById<TextView>(R.id.ticket_release_date)
        val ticketTotalPriceView = findViewById<TextView>(R.id.ticket_total_price)

        with(movieInfo) {
            ticketTitleView.text = title
            ticketMovieReleaseDateView.text = ticket.date.format(dateTimeFormatter)
            ticketCountView.text = getString(R.string.movie_ticket_count).format(ticket.count)
            ticketTotalPriceView.text = getString(R.string.movie_ticket_total_price).format(decimalFormat.format(ticket.getTotalPrice()))
        }
    }

    companion object {
        val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        val decimalFormat = DecimalFormat("#,###")
    }
}
