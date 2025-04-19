package woowacourse.movie.activity

import android.icu.text.DecimalFormat
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.Ticket
import java.time.format.DateTimeFormatter

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
                @Suppress
                intent.getParcelableExtra(Ticket.KEY_TICKET) ?: throw IllegalArgumentException()
            }

        setTicketInfo(ticket)
    }

    private fun setTicketInfo(ticket: Ticket) {
        val formatter = DateTimeFormatter.ofPattern(DATETIME_PATTERN)
        val dateTimeFormat = ticket.date.format(formatter)

        val movieTitleTextView = findViewById<TextView>(R.id.movie_title)
        val movieCancelInfoTextView = findViewById<TextView>(R.id.cancel_info_Text)
        val movieDateTextView = findViewById<TextView>(R.id.movie_date)
        val moviePersonnel = findViewById<TextView>(R.id.movie_personnel)
        val movieTotalPrice = findViewById<TextView>(R.id.movie_total_price)

        movieTitleTextView.text = ticket.title
        movieCancelInfoTextView.text = getString(R.string.movie_cancel_deadline, CANCEL_DEADLINE)
        movieDateTextView.text = dateTimeFormat
        moviePersonnel.text = getString(R.string.moviePersonnel, ticket.personnel)
        movieTotalPrice.text = getString(R.string.movieTotalPrice, totalPrice(ticket.personnel))
    }

    private fun totalPrice(personnel: Int): String {
        val priceFormatter = DecimalFormat(PRICE_PATTERN)
        return priceFormatter.format(DEFAULT_PRICE * personnel).toString()
    }

    companion object {
        private const val DATETIME_PATTERN = "yyyy.M.d. HH:mm"
        private const val PRICE_PATTERN = "#,###"
        private const val DEFAULT_PRICE = 13_000
        private const val CANCEL_DEADLINE = 15
    }
}
