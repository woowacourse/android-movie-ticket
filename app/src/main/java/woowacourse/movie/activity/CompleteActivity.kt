package woowacourse.movie.activity

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.utils.DateFormatter
import woowacourse.movie.utils.PriceFormatter
import woowacourse.movie.R
import woowacourse.movie.domain.Ticket

class CompleteActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_complete)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val ticket: Ticket =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra(Ticket.KEY_TICKET, Ticket::class.java) ?: throw IllegalArgumentException()
            } else {
                @Suppress("DEPRECATION")
                intent.getParcelableExtra(Ticket.KEY_TICKET) ?: throw IllegalArgumentException()
            }

        setTicketInfo(ticket)
    }

    private fun setTicketInfo(ticket: Ticket) {
        val dateFormatter = DateFormatter()
        val formattedDateTime = dateFormatter.format(ticket.date)
        val priceFormatter = PriceFormatter()
        val formattedPrice = priceFormatter.format(DEFAULT_PRICE * ticket.personnel)

        val movieTitleTextView = findViewById<TextView>(R.id.movie_title)
        val movieCancelInfoTextView = findViewById<TextView>(R.id.cancel_info_Text)
        val movieDateTextView = findViewById<TextView>(R.id.movie_date)
        val moviePersonnel = findViewById<TextView>(R.id.movie_personnel)
        val movieTotalPrice = findViewById<TextView>(R.id.movie_total_price)

        movieTitleTextView.text = ticket.title
        movieCancelInfoTextView.text = getString(R.string.movie_cancel_deadline, CANCEL_DEADLINE)
        movieDateTextView.text = formattedDateTime
        moviePersonnel.text = getString(R.string.moviePersonnel, ticket.personnel)
        movieTotalPrice.text = getString(R.string.movieTotalPrice, formattedPrice)
    }

    companion object {
        private const val DEFAULT_PRICE = 13_000
        private const val CANCEL_DEADLINE = 15
    }
}
