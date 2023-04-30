package woowacourse.movie.movieTicket

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import model.MovieTicketModel
import woowacourse.movie.R
import woowacourse.movie.utils.getSerializableExtraCompat
import woowacourse.movie.utils.keyError

class MovieTicketActivity : AppCompatActivity() {
    private val activityView by lazy { window.decorView.rootView }

    private lateinit var ticket: MovieTicketModel
    private lateinit var ticketView: MovieTicketView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_ticket)

        ticket = intent.getSerializableExtraCompat(KEY_MOVIE_TICKET) ?: return keyError(KEY_MOVIE_TICKET)
        ticketView = MovieTicketView(activityView)

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
        private const val KEY_MOVIE_TICKET = "key_movie_ticket"

        fun start(context: Context, ticket: MovieTicketModel) {
            context.startActivity(
                Intent(context, MovieTicketActivity::class.java).apply {
                    putExtra(KEY_MOVIE_TICKET, ticket)
                },
                null,
            )
        }
    }
}
