package woowacourse.movie.completedbooking

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.Ticket
import woowacourse.movie.utils.DateFormatter
import woowacourse.movie.utils.PriceFormatter
import woowacourse.movie.utils.version

class CompletedBookingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_completed_booking)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_completed_booking_root)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ticket: Ticket = intent.version(KEY_TICKET, Ticket::class.java)

        setTicketInfo(ticket)
    }

    private fun setTicketInfo(ticket: Ticket) {
        val dateFormatter = DateFormatter()
        val formattedDateTime = dateFormatter.format(ticket.date)
        val priceFormatter = PriceFormatter()
        val formattedPrice = priceFormatter.format(DEFAULT_PRICE * ticket.personnel)

        val movieTitleTextView = findViewById<TextView>(R.id.ticket_movie_title)
        val movieCancelInfoTextView = findViewById<TextView>(R.id.ticket_cancel_info_Text)
        val movieDateTextView = findViewById<TextView>(R.id.ticket_movie_datetime)
        val moviePersonnel = findViewById<TextView>(R.id.ticket_movie_personnel)
        val movieTotalPrice = findViewById<TextView>(R.id.ticket_total_price)

        movieTitleTextView.text = ticket.title
        movieCancelInfoTextView.text = getString(R.string.movie_cancel_deadline, CANCEL_DEADLINE)
        movieDateTextView.text = formattedDateTime
        moviePersonnel.text = getString(R.string.moviePersonnel, ticket.personnel)
        movieTotalPrice.text = getString(R.string.movieTotalPrice, formattedPrice)
    }

    companion object {
        private const val KEY_TICKET = "ticket"
        private const val DEFAULT_PRICE = 13_000
        private const val CANCEL_DEADLINE = 15

        fun newIntent(
            context: Context,
            ticket: Ticket,
        ): Intent {
            return Intent(context, CompletedBookingActivity::class.java).putExtra(KEY_TICKET, ticket)
        }
    }
}
