package woowacourse.movie.view

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.Ticket
import woowacourse.movie.util.getParcelableCompat
import java.time.format.DateTimeFormatter

class MovieReservationResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()
        initializeReservationInfo()
    }

    private fun initializeView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_reservation_completion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initializeReservationInfo() {
        val title = findViewById<TextView>(R.id.movie_title)
        val showtime = findViewById<TextView>(R.id.showtime)
        val ticketCount = findViewById<TextView>(R.id.ticket_count)
        val totalPrice = findViewById<TextView>(R.id.total_price)

        val ticket: Ticket =
            intent.extras?.getParcelableCompat<Ticket>(MovieReservationActivity.KEY_TICKET)
                ?: run { return }
        title.text = ticket.movie.title
        showtime.text =
            ticket.showtime.format(DateTimeFormatter.ofPattern(getString(R.string.date_time_format)))
        ticketCount.text = getString(R.string.ticket_count_format).format(ticket.count)
        totalPrice.text = getString(R.string.ticket_price_format).format(ticket.totalPrice())
    }
}
