package woowacourse.movie.view

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.Ticket
import woowacourse.movie.view.model.TicketData
import java.time.LocalDateTime

class TicketActivity : AppCompatActivity() {
    private lateinit var ticket: Ticket
    private val ticketData: TicketData by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) { // API 33
            intent.getParcelableExtra(ReservationActivity.EXTRA_TICKET_DATA, TicketData::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra(ReservationActivity.EXTRA_TICKET_DATA)
        } ?: throw IllegalArgumentException("티켓 정보가 전달되지 않았습니다")
    }

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
        val screening = ticketData.screeningData.toScreening()
        val ticketCount = ticketData.ticketCount
        val showtime: LocalDateTime = ticketData.showtime

        ticket =
            Ticket(
                screening = screening,
                count = ticketCount,
                showtime = showtime,
            )
    }

    private fun initViews() {
        val titleView = findViewById<TextView>(R.id.tv_ticket_movie_title)
        titleView.text = ticket.title

        val showtimeView = findViewById<TextView>(R.id.tv_ticket_screening_date)
        showtimeView.text =
            ticket.showtime.run {
                getString(R.string.ticket_showtime, year, monthValue, dayOfMonth, hour, minute)
            }

        val countView = findViewById<TextView>(R.id.tv_ticket_count)
        countView.text = getString(R.string.ticket_count, ticket.count)

        val priceView = findViewById<TextView>(R.id.tv_ticket_price)
        priceView.text = getString(R.string.ticket_price, ticket.price)
    }
}
