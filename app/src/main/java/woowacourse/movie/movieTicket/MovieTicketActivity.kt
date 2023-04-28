package woowacourse.movie.movieTicket

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import model.MovieTicketModel
import woowacourse.movie.R
import woowacourse.movie.utils.getSerializableExtraCompat

class MovieTicketActivity : AppCompatActivity() {
    private val ticket: MovieTicketModel by lazy {
        intent.getSerializableExtraCompat(KEY_MOVIE_TICKET) as? MovieTicketModel ?: run {
            finish()
            Toast.makeText(this, INVALID_MOVIE_SCREENING, Toast.LENGTH_LONG).show()
            MovieTicketModel.EMPTY
        }
    }

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
        private const val INVALID_MOVIE_SCREENING = "잘못된 접근입니다."
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
