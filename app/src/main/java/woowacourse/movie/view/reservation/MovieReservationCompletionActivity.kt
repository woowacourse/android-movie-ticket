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
import woowacourse.movie.domain.Ticket
import woowacourse.movie.view.common.IntentKeys
import woowacourse.movie.view.common.parcelableExtra
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
            intent.parcelableExtra(IntentKeys.EXTRA_TICKET, Ticket::class.java)
                ?: run {
                    finish()
                    return
                }

        val title = findViewById<TextView>(R.id.movie_title)
        val showtime = findViewById<TextView>(R.id.showtime)
        val ticketCount = findViewById<TextView>(R.id.ticket_count)
        val totalPrice = findViewById<TextView>(R.id.total_price)

        title.text = ticket.movie.title
        showtime.text = ticket.showtime.format(DATE_FORMAT)
        ticketCount.text = getString(R.string.ticket_count).format(ticket.count)
        totalPrice.text = getString(R.string.total_price).format(ticket.totalPrice())
    }

    companion object {
        fun newIntent(
            context: Context,
            ticket: Ticket,
        ): Intent =
            Intent(context, MovieReservationCompletionActivity::class.java).apply {
                putExtra(IntentKeys.EXTRA_TICKET, ticket)
            }

        private val DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")
    }
}
