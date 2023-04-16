package woowacourse.movie.movieTicket

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import entity.MovieTicket
import woowacourse.movie.R

class MovieTicketActivity : AppCompatActivity() {
    private val ticket by lazy { intent.getSerializableExtra(KEY_MOVIE_TICKET) as? MovieTicket ?: throw IllegalArgumentException() }

    private val activityView by lazy { window.decorView.rootView }

    private val ticketView by lazy { MovieTicketView(activityView) }
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
        ticketView.update(ticket)
    }

    companion object {
        const val KEY_MOVIE_TICKET = "movieTicket"
    }
}
