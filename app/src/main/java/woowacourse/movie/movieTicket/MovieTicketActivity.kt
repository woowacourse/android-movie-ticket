package woowacourse.movie.movieTicket

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import model.TicketModel
import woowacourse.movie.R
import woowacourse.movie.utils.getSerializableExtraCompat

class MovieTicketActivity : AppCompatActivity() {
    private val ticket: TicketModel by lazy {
        intent.getSerializableExtraCompat(KEY_MOVIE_TICKET) as? TicketModel ?: run {
            finish()
            Toast.makeText(this, INVALID_MOVIE_SCREENING, Toast.LENGTH_LONG).show()
            TicketModel.EMPTY
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
        const val INVALID_MOVIE_SCREENING = "잘못된 접근입니다."
        const val KEY_MOVIE_TICKET = "movieTicket"
    }
}
