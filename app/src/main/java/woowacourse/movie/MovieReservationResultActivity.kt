package woowacourse.movie

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.domain.Ticket
import woowacourse.movie.util.getParcelableCompat
import java.time.format.DateTimeFormatter

class MovieReservationResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeView()

        val ticket: Ticket =
            intent.extras?.getParcelableCompat<Ticket>(MovieReservationActivity.KEY_TICKET) ?: run {
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

    private fun initializeView() {
        enableEdgeToEdge()
        setContentView(R.layout.activity_movie_reservation_completion)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    companion object {
        private const val TICKET_COUNT = "%d명"
        private const val TOTAL_PRICE = "%,d원"
        private val DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")
    }
}
