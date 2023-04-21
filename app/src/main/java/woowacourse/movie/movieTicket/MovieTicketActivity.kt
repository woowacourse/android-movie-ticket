package woowacourse.movie.movieTicket

import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.extension.getSerializableTicketOrNull
import woowacourse.movie.uimodel.MovieTicketUi
import woowacourse.movie.utils.DateUtil
import woowacourse.movie.view.decimalFormat

class MovieTicketActivity : AppCompatActivity() {

    private val movieTicketUi by lazy {
        intent.getSerializableTicketOrNull()
            ?: run {
                finish()
                MovieTicketUi.EMPTY
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_ticket)
        initToolBar()
        initMovieView()
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

    private fun initToolBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initMovieView() {
        val ticketTitleView = findViewById<TextView>(R.id.ticket_movie_title)
        val ticketCountView = findViewById<TextView>(R.id.ticket_total_ticket_count)
        val ticketMovieReleaseDateView = findViewById<TextView>(R.id.ticket_release_date)
        val ticketTotalPriceView = findViewById<TextView>(R.id.ticket_total_price)
        val context = this

        val seats = movieTicketUi.seats.joinToString(", ") { it }

        with(movieTicketUi) {
            ticketTitleView.text = title
            ticketCountView.text = getString(R.string.movie_ticket_count).format(count.toInt())
            ticketTotalPriceView.text = getString(R.string.movie_ticket_receipt)
                .format(decimalFormat.format(movieTicketUi.totalPrice), seats)
            "${DateUtil(context).getDate(date)} ${DateUtil(context).getTime(time)}"
                .also { ticketMovieReleaseDateView.text = it }
        }
    }

    companion object {
        const val KEY_MOVIE_TICKET = "movieTicketUi"
    }
}
