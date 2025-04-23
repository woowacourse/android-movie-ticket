package woowacourse.movie.view.ticket

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
import woowacourse.movie.domain.ticket.CancelTimePolicy
import woowacourse.movie.domain.ticket.Ticket
import woowacourse.movie.view.util.ErrorMessage
import java.time.LocalDateTime

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
        ticket = intent.getTicketExtra(EXTRA_TICKET) ?: error(
            ErrorMessage(CAUSE_TICKET).notProvided(),
        )
    }

    @Suppress("DEPRECATION")
    private fun Intent.getTicketExtra(key: String): Ticket? =
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU ->
                getSerializableExtra(
                    key,
                    Ticket::class.java,
                )

            else -> getSerializableExtra(key) as? Ticket
        }

    private fun initViews() {
        initCancelDescriptionView()
        initTitleView()
        initShowtimeView()
        initCountView()
        initPriceView()
    }

    private fun initCancelDescriptionView() {
        val cancelDescriptionView = findViewById<TextView>(R.id.tv_ticket_cancel_description)
        cancelDescriptionView.text =
            getString(
                R.string.ticket_cancel_time_description,
                CancelTimePolicy.CANCELABLE_MINUTES,
            )
    }

    private fun initPriceView() {
        val ticket: Ticket = ticket ?: error(ErrorMessage(CAUSE_TICKET).notProvided())
        val priceView = findViewById<TextView>(R.id.tv_ticket_price)
        priceView.text = getString(R.string.ticket_price, ticket.price)
    }

    private fun initCountView() {
        val ticket: Ticket = ticket ?: error(ErrorMessage(CAUSE_TICKET).notProvided())
        val countView = findViewById<TextView>(R.id.tv_ticket_count)
        countView.text = getString(R.string.ticket_count, ticket.count)
    }

    private fun initShowtimeView() {
        val ticket: Ticket = ticket ?: error(ErrorMessage(CAUSE_TICKET).notProvided())
        val showtimeView = findViewById<TextView>(R.id.tv_ticket_screening_date)
        showtimeView.text =
            ticket.showtime.run {
                getString(R.string.ticket_showtime, year, monthValue, dayOfMonth, hour, minute)
            }
    }

    private fun initTitleView() {
        val ticket: Ticket = ticket ?: error(ErrorMessage(CAUSE_TICKET).notProvided())
        val titleView = findViewById<TextView>(R.id.tv_ticket_movie_title)
        titleView.text = ticket.title
    }

    companion object {
        private const val CAUSE_TICKET = "ticket"

        private const val EXTRA_TICKET = "woowacourse.movie.EXTRA_TICKET"

        fun newIntent(
            context: Context,
            title: String,
            count: Int,
            showtime: LocalDateTime,
        ): Intent =
            run {
                val ticket = Ticket(title, count, showtime)
                Intent(context, TicketActivity::class.java)
                    .putExtra(EXTRA_TICKET, ticket)
            }
    }
}
