package woowacourse.movie.view.ticket

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.ticket.Ticket
import woowacourse.movie.view.reservation.ReservationActivity
import woowacourse.movie.view.util.ErrorMessage

class TicketActivity : AppCompatActivity() {
    private var ticket: Ticket? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ticket)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_ticket)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initModel()
        initViews()
    }

    private fun initModel() {
        ticket = intent.getTicketExtra(ReservationActivity.Companion.EXTRA_TICKET)
            ?: error(ErrorMessage("ticket").notProvided())
    }

    @Suppress("DEPRECATION")
    private fun Intent.getTicketExtra(key: String): Ticket? =
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU ->
                getParcelableExtra(
                    key,
                    Ticket::class.java,
                )

            else -> getParcelableExtra(key) as? Ticket
        }

    private fun initViews() {
        initTitleView()
        initShowtimeView()
        initCountView()
        initPriceView()
    }

    private fun initPriceView() {
        val ticket: Ticket = ticket ?: error(ErrorMessage("ticket").notProvided())
        val priceView = findViewById<TextView>(R.id.tv_ticket_price)
        priceView.text = getString(R.string.ticket_price, ticket.price)
    }

    private fun initCountView() {
        val ticket: Ticket = ticket ?: error(ErrorMessage("ticket").notProvided())
        val countView = findViewById<TextView>(R.id.tv_ticket_count)
        countView.text = getString(R.string.ticket_count, ticket.count)
    }

    private fun initShowtimeView() {
        val ticket: Ticket = ticket ?: error(ErrorMessage("ticket").notProvided())
        val showtimeView = findViewById<TextView>(R.id.tv_ticket_screening_date)
        showtimeView.text =
            ticket.showtime.run {
                getString(R.string.ticket_showtime, year, monthValue, dayOfMonth, hour, minute)
            }
    }

    private fun initTitleView() {
        val ticket: Ticket = ticket ?: error(ErrorMessage("ticket").notProvided())
        val titleView = findViewById<TextView>(R.id.tv_ticket_movie_title)
        titleView.text = ticket.title
    }
}
