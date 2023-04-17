package woowacourse.movie.movieTicket

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.extension.getSerializableTicketOrNull
import woowacourse.movie.uimodel.MovieTicketUi
import woowacourse.movie.utils.DateUtil
import woowacourse.movie.utils.toDomain
import woowacourse.movie.view.decimalFormat

class MovieTicketActivity : AppCompatActivity() {

    private val ticketUi by lazy {
        intent.getSerializableTicketOrNull()
            ?: run {
                finish()
                Toast.makeText(this, "잘못된 접근입니다.", Toast.LENGTH_SHORT).show()
                MovieTicketUi.EMPTY_STATE
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_ticket)
        registerToolbar()
        updateMovieView()
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

    private fun registerToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun updateMovieView() {
        val ticketTitleView = findViewById<TextView>(R.id.ticket_movie_title)
        val ticketCountView = findViewById<TextView>(R.id.ticket_total_ticket_count)
        val ticketMovieReleaseDateView = findViewById<TextView>(R.id.ticket_release_date)
        val ticketTotalPriceView = findViewById<TextView>(R.id.ticket_total_price)
        val context = this

        with(ticketUi) {
            ticketTitleView.text = title
            ticketMovieReleaseDateView.text = DateUtil(context).getDate(date)
            ticketCountView.text = getString(R.string.movie_ticket_count).format(count.toInt())
            ticketTotalPriceView.text = getString(R.string.movie_ticket_total_price).format(decimalFormat.format(this.toDomain().getTotalPrice()))
        }
    }

    companion object {
        const val KEY_MOVIE_TICKET = "movieTicketUi"
    }
}
