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
        ticket =
            intent.run {
                val title: String =
                    getStringExtra(EXTRA_TICKET_TITLE) ?: error(ErrorMessage("title").notProvided())
                val count: Int =
                    getIntExtra(
                        EXTRA_TICKET_COUNT,
                        TICKET_COUNT_NOT_PROVIDED,
                    ).takeIf { it != TICKET_COUNT_NOT_PROVIDED }
                        ?: error(ErrorMessage("ticket count").notProvided())
                val showtime: LocalDateTime =
                    getLocalDateTimeExtra(EXTRA_SHOWTIME) ?: error(
                        ErrorMessage("showtime").notProvided(),
                    )
                Ticket(title, count, showtime)
            }
    }

    @Suppress("DEPRECATION")
    private fun Intent.getLocalDateTimeExtra(key: String): LocalDateTime? =
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU ->
                getSerializableExtra(
                    key,
                    LocalDateTime::class.java,
                )

            else -> getSerializableExtra(key) as? LocalDateTime
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

    companion object {
        private const val TICKET_COUNT_NOT_PROVIDED = -1

        private const val EXTRA_TICKET_TITLE = "woowacourse.movie.EXTRA_TICKET_TITLE"
        private const val EXTRA_TICKET_COUNT = "woowacourse.movie.EXTRA_TICKET_COUNT"
        private const val EXTRA_SHOWTIME = "woowacourse.movie.EXTRA_SHOWTIME"

        fun newIntent(
            context: Context,
            title: String,
            count: Int,
            showtime: LocalDateTime,
        ): Intent =
            Intent(context, TicketActivity::class.java)
                .putExtra(EXTRA_TICKET_TITLE, title)
                .putExtra(EXTRA_TICKET_COUNT, count)
                .putExtra(EXTRA_SHOWTIME, showtime)
    }
}
