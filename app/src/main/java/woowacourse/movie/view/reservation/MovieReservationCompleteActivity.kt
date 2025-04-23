package woowacourse.movie.view.reservation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.common.parcelableExtra
import woowacourse.movie.domain.Ticket
import java.time.format.DateTimeFormatter

class MovieReservationCompleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_reservation_complete)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ticket =
            intent.parcelableExtra(EXTRA_TICKET, Ticket::class.java) ?: finish().run { return }
        bindTicketInfo(ticket)
    }

    private fun bindTicketInfo(ticket: Ticket) {
        findViewById<TextView>(R.id.movie_title).text = ticket.movie.title
        findViewById<TextView>(R.id.showtime).text = ticket.showtime.format(DATE_FORMAT)
        findViewById<TextView>(R.id.ticket_count).text =
            getString(R.string.ticket_count, ticket.count)
        findViewById<TextView>(R.id.total_price).text =
            getString(R.string.total_price, ticket.totalPrice())
    }

    companion object {
        fun newIntent(
            context: Context,
            ticket: Ticket,
        ): Intent =
            Intent(context, MovieReservationCompleteActivity::class.java).apply {
                putExtra(EXTRA_TICKET, ticket)
            }

        private const val EXTRA_TICKET = "extra_ticket"
        private val DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")
    }
}
