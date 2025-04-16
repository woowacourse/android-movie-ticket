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
import woowacourse.movie.view.ReservationActivity.Companion.EXTRA_SHOWTIME_DAY
import woowacourse.movie.view.ReservationActivity.Companion.EXTRA_SHOWTIME_HOUR
import woowacourse.movie.view.ReservationActivity.Companion.EXTRA_SHOWTIME_MONTH
import woowacourse.movie.view.ReservationActivity.Companion.EXTRA_SHOWTIME_YEAR
import woowacourse.movie.view.ReservationActivity.Companion.EXTRA_TICKET_COUNT
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

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

        val startYear = intent.getIntExtra(MainActivity.EXTRA_START_YEAR, 0)
        val startMonth = intent.getIntExtra(MainActivity.EXTRA_START_MONTH, 0)
        val startDay = intent.getIntExtra(MainActivity.EXTRA_START_DAY, 0)
        val endYear = intent.getIntExtra(MainActivity.EXTRA_END_YEAR, 0)
        val endMonth = intent.getIntExtra(MainActivity.EXTRA_END_MONTH, 0)
        val endDay = intent.getIntExtra(MainActivity.EXTRA_END_DAY, 0)

        val startDate = LocalDate.of(startYear, startMonth, startDay)
        val endDate = LocalDate.of(endYear, endMonth, endDay)

        val screening =
            Screening(
                movie,
                startDate..endDate,
            )

        val ticketCount = intent.getIntExtra(EXTRA_TICKET_COUNT, 0)

        val showtimeYear = intent.getIntExtra(EXTRA_SHOWTIME_YEAR, 0)
        val showtimeMonth = intent.getIntExtra(EXTRA_SHOWTIME_MONTH, 0)
        val showtimeDay = intent.getIntExtra(EXTRA_SHOWTIME_DAY, 0)
        val showtimeHour = LocalTime.of(intent.getIntExtra(EXTRA_SHOWTIME_HOUR, 0), 0)
        val showtimeDate = LocalDate.of(showtimeYear, showtimeMonth, showtimeDay)

        ticket =
            Ticket(
                screening = screening,
                count = ticketCount,
                showtime = LocalDateTime.of(showtimeDate, showtimeHour),
            )
    }

    private fun initViews() {
        val titleView = findViewById<TextView>(R.id.tv_ticket_movie_title)
        titleView.text = ticket.title

        val showtimeView = findViewById<TextView>(R.id.tv_ticket_screening_date)
        showtimeView.text = ticket.showtime.toString()

        val countView = findViewById<TextView>(R.id.tv_ticket_count)
        countView.text = getString(R.string.ticket_count, ticket.count)

        val priceView = findViewById<TextView>(R.id.tv_ticket_price)
        priceView.text = getString(R.string.ticket_price, ticket.price.toString())
    }
}
