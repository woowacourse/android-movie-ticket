package woowacourse.movie.view

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Screening
import woowacourse.movie.domain.Ticket
import woowacourse.movie.view.MainActivity.Companion.EXTRA_POSTER_ID
import woowacourse.movie.view.MainActivity.Companion.EXTRA_RUNNING_TIME
import woowacourse.movie.view.MainActivity.Companion.EXTRA_TITLE
import woowacourse.movie.view.ReservationActivity.Companion.EXTRA_END_DATE
import woowacourse.movie.view.ReservationActivity.Companion.EXTRA_SHOWTIME
import woowacourse.movie.view.ReservationActivity.Companion.EXTRA_START_DATE
import woowacourse.movie.view.ReservationActivity.Companion.EXTRA_TICKET_COUNT
import java.time.LocalDate
import java.time.LocalDateTime

class TicketActivity : AppCompatActivity() {
    private lateinit var ticket: Ticket

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
        val title = intent.getStringExtra(EXTRA_TITLE) ?: error("")
        val runningTime = intent.getIntExtra(EXTRA_RUNNING_TIME, 0)

        @DrawableRes val posterId = intent.getIntExtra(EXTRA_POSTER_ID, 0)

        val movie =
            Movie(
                title,
                runningTime,
                posterId,
            )

        val startDate = LocalDate.parse(intent.getStringExtra(EXTRA_START_DATE))
        val endDate = LocalDate.parse(intent.getStringExtra(EXTRA_END_DATE))

        val screening =
            Screening(
                movie,
                startDate..endDate,
            )

        val ticketCount = intent.getIntExtra(EXTRA_TICKET_COUNT, 0)
        val showtime: LocalDateTime = LocalDateTime.parse(intent.getStringExtra(EXTRA_SHOWTIME))

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
