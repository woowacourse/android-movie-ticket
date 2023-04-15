package woowacourse.movie.movieTicket

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import entity.MovieTicket
import woowacourse.movie.R

class MovieTicketActivity : AppCompatActivity() {
    private val ticket by lazy { intent.getSerializableExtra(KEY_MOVIE_TICKET) as? MovieTicket ?: throw IllegalArgumentException() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_ticket)

        initToolbar()
        initTicketView()
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

    private fun initToolbar() {
        val reservationToolbar = findViewById<Toolbar>(R.id.ticket_toolbar)
        setSupportActionBar(reservationToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initTicketView() {
        MovieTicketView(
            titleView = findViewById(R.id.ticket_movie_title),
            countView = findViewById(R.id.ticket_total_ticket_count),
            releaseDateView = findViewById(R.id.ticket_release_date),
            totalPriceView = findViewById(R.id.ticket_total_price),
        ).bind(this, ticket)
    }

    companion object {
        const val KEY_MOVIE_TICKET = "movieTicket"
    }
}
