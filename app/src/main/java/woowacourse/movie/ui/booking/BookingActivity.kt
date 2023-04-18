package woowacourse.movie.ui.booking

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.Movie
import woowacourse.movie.MovieMapper.poster
import woowacourse.movie.MovieRepository
import woowacourse.movie.R
import woowacourse.movie.ScreeningTimes
import woowacourse.movie.TicketCount
import woowacourse.movie.formatScreenDate
import woowacourse.movie.ui.completed.CompletedActivity
import java.time.LocalDate
import java.time.LocalTime

class BookingActivity : AppCompatActivity() {
    private var ticketCount = TicketCount()
    private val dateTimeSpinner by lazy {
        findViewById<DateTimeSpinner>(R.id.spinnerDateTime)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)
        val movie = getMovie()
        initDateTimes(movie)
        restoreData(savedInstanceState)
        initView(movie)
        gatherClickListeners(movie)
        initSpinnerListener()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun restoreData(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            with(savedInstanceState) {
                ticketCount = TicketCount(getInt(TICKET_COUNT))
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putInt(TICKET_COUNT, ticketCount.value)
        }
        super.onSaveInstanceState(outState)
    }

    private fun getMovie(): Movie {
        val movieId = intent.getLongExtra(MOVIE_ID, -1)
        return MovieRepository.getMovie(movieId)
    }

    private fun gatherClickListeners(movie: Movie) {
        clickMinus()
        clickPlus()
        clickBookingComplete(movie)
    }

    private fun initView(movie: Movie) {
        findViewById<ImageView>(R.id.imageBookingPoster).setImageResource(movie.poster)
        findViewById<TextView>(R.id.textBookingTitle).text = movie.title
        findViewById<TextView>(R.id.textBookingScreeningDate).text =
            getString(
                R.string.screening_date,
                movie.startDate.formatScreenDate(),
                movie.endDate.formatScreenDate(),
            )
        findViewById<TextView>(R.id.textBookingRunningTime).text =
            getString(R.string.running_time, movie.runningTime)
        findViewById<TextView>(R.id.textBookingDescription).text = movie.description
        findViewById<TextView>(R.id.textBookingTicketCount).text = ticketCount.value.toString()
    }

    private fun clickMinus() {
        findViewById<Button>(R.id.buttonBookingMinus).setOnClickListener {
            ticketCount = ticketCount.minus()
            findViewById<TextView>(R.id.textBookingTicketCount).text = ticketCount.value.toString()
        }
    }

    private fun clickPlus() {
        findViewById<Button>(R.id.buttonBookingPlus).setOnClickListener {
            ticketCount = ticketCount.plus()
            findViewById<TextView>(R.id.textBookingTicketCount).text = ticketCount.value.toString()
        }
    }

    private fun clickBookingComplete(movie: Movie) {
        findViewById<Button>(R.id.buttonBookingComplete).setOnClickListener {
            val dateTime = dateTimeSpinner.selectedDateTime
            val ticket = movie.reserve(dateTime, ticketCount.value)
            startActivity(CompletedActivity.getIntent(this, ticket))
        }
    }

    private fun initDateTimes(movie: Movie) {
        val dates: List<LocalDate> = movie.screeningDates
        val times: List<LocalTime> = ScreeningTimes.getScreeningTime(dates[0])
        dateTimeSpinner.initDateItems(dates)
        dateTimeSpinner.initTimeItems(times)
    }

    private fun initSpinnerListener() {
        dateTimeSpinner.initDateSelectedListener { ScreeningTimes.getScreeningTime(it) }
    }

    companion object {
        private const val MOVIE_ID = "MOVIE_ID"
        private const val TICKET_COUNT = "TICKET_COUNT"

        fun getIntent(context: Context, movieId: Long): Intent {
            return Intent(context, BookingActivity::class.java).apply {
                putExtra(MOVIE_ID, movieId)
            }
        }
    }
}
