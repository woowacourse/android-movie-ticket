package woowacourse.movie.view.ticket

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.extension.getParcelableExtraCompat
import woowacourse.movie.model.ticket.Ticket
import woowacourse.movie.presenter.TicketPresenter
import woowacourse.movie.view.model.TicketData

class TicketActivity :
    AppCompatActivity(),
    TicketView {
    private val present: TicketPresenter = TicketPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ticket)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.layout_ticket)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        present.initTicketUi()
    }

    override fun getTicketData(): TicketData =
        intent.getParcelableExtraCompat<TicketData>(EXTRA_TICKET_DATA)
            ?: throw IllegalArgumentException(ERROR_CANT_READ_TICKET_INFO)

    override fun initTicketUI(ticket: Ticket) {
        val titleView = findViewById<TextView>(R.id.tv_ticket_movie_title)
        titleView.text = ticket.title

        val showtimeView = findViewById<TextView>(R.id.tv_ticket_screening_date)
        showtimeView.text =
            ticket.showtime.run {
                getString(R.string.ticket_showtime, year, monthValue, dayOfMonth, hour, minute)
            }

        val cancelableMinuteView = findViewById<TextView>(R.id.tv_ticket_cancel_time_info)
        cancelableMinuteView.text =
            getString(R.string.ticket_cancelable_minute_info, ticket.cancelableMinute)

        val countView = findViewById<TextView>(R.id.tv_ticket_count)
        countView.text =
            getString(
                R.string.ticket_count,
                ticket.ticketCount.value,
                present.getSortedSeatsCodes().joinToString(),
            )

        val priceView = findViewById<TextView>(R.id.tv_ticket_price)
        priceView.text = getString(R.string.ticket_price, present.getTotalPrice().value)
    }

    companion object {
        private const val ERROR_CANT_READ_TICKET_INFO = "티켓 정보가 전달되지 않았습니다"

        private const val EXTRA_TICKET_DATA = "woowacourse.movie.EXTRA_TICKET_DATA"

        fun newIntent(
            context: Context,
            ticketData: TicketData,
        ): Intent =
            Intent(context, TicketActivity::class.java).apply {
                putExtra(EXTRA_TICKET_DATA, ticketData)
            }
    }
}
