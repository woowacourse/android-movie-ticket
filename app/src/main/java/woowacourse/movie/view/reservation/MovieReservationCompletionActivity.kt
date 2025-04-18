package woowacourse.movie.view.reservation

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
import woowacourse.movie.view.reservation.MovieReservationActivity.Companion.EXTRA_TICKET
import java.time.format.DateTimeFormatter

class MovieReservationCompletionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_reservation_completion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val ticket =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent.getParcelableExtra(EXTRA_TICKET, Ticket::class.java)
            } else {
                @Suppress("DEPRECATION")
                intent.getParcelableExtra(EXTRA_TICKET)
            }
        if (ticket == null) {
            finish()
            return
        }

        val title = findViewById<TextView>(R.id.movie_title)
        val showtime = findViewById<TextView>(R.id.showtime)
        val ticketCount = findViewById<TextView>(R.id.ticket_count)
        val totalPrice = findViewById<TextView>(R.id.total_price)

        title.text = ticket.movie.title
        showtime.text = ticket.showtime.format(DATE_FORMAT)
        ticketCount.text = TICKET_COUNT.format(ticket.count)
        totalPrice.text = TOTAL_PRICE.format(ticket.totalPrice())
    }

    companion object {
        fun newIntent(
            context: Context,
            ticket: Ticket,
        ): Intent =
            Intent(context, MovieReservationCompletionActivity::class.java).apply {
                putExtra(EXTRA_TICKET, ticket)
            }

        private const val TICKET_COUNT = "%d명"
        private const val TOTAL_PRICE = "%,d원"
        private val DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")
    }
}
