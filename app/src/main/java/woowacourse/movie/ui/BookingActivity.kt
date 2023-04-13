package woowacourse.movie.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.MovieData
import woowacourse.movie.domain.ScreeningTimes
import woowacourse.movie.domain.Ticket
import woowacourse.movie.domain.TicketCount
import woowacourse.movie.formatScreenDate
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class BookingActivity : AppCompatActivity() {
    private var ticketCount = TicketCount()
    private val dateSpinner by lazy { findViewById<Spinner>(R.id.spinnerScreeningDate) }
    private val timeSpinner by lazy { findViewById<Spinner>(R.id.spinnerScreeningTime) }
    private val dateSpinnerAdapter by lazy {
        SpinnerAdapter<LocalDate>(this, R.layout.screening_date_time_item, R.id.textSpinnerDateTime)
    }
    private val timeSpinnerAdapter by lazy {
        SpinnerAdapter<LocalTime>(this, R.layout.screening_date_time_item, R.id.textSpinnerDateTime)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)
        val movie = getMovie()
        initAdapters()
        initDateTimes(movie)
        restoreData(savedInstanceState)
        initView(movie)
        gatherClickListeners()
        initDateSpinnerSelectedListener(movie)
    }

    private fun restoreData(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            with(savedInstanceState) {
                ticketCount = TicketCount(getInt(TICKET_COUNT))
                dateSpinner.setSelection(getInt(DATE_POSITION), false)
                timeSpinner.setSelection(getInt(TIME_POSITION), false)
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putInt(TICKET_COUNT, ticketCount.value)
            putInt(DATE_POSITION, dateSpinner.selectedItemPosition)
            putInt(TIME_POSITION, timeSpinner.selectedItemPosition)
        }
        super.onSaveInstanceState(outState)
    }

    private fun getMovie(): Movie {
        val movieId = intent.getLongExtra(MOVIE_ID, -1)
        return MovieData.findMovieById(movieId)
    }

    private fun gatherClickListeners() {
        clickMinus()
        clickPlus()
        clickBookingComplete()
    }

    private fun initView(movie: Movie) {
        findViewById<ImageView>(R.id.imageBookingPoster).setImageResource(movie.poster)
        findViewById<TextView>(R.id.textBookingTitle).text = movie.title
        findViewById<TextView>(R.id.textBookingScreeningDate).text =
            getString(R.string.screening_date).format(
                movie.screeningStartDate.formatScreenDate(),
                movie.screeningEndDate.formatScreenDate(),
            )
        findViewById<TextView>(R.id.textBookingRunningTime).text =
            getString(R.string.running_time).format(movie.runningTime)
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

    private fun clickBookingComplete() {
        findViewById<Button>(R.id.buttonBookingComplete).setOnClickListener {
            val movieId = intent.getLongExtra(MOVIE_ID, -1)
            val dateTime = LocalDateTime.of(
                dateSpinnerAdapter.getItem(findViewById<Spinner>(R.id.spinnerScreeningDate).selectedItemPosition),
                timeSpinnerAdapter.getItem(findViewById<Spinner>(R.id.spinnerScreeningTime).selectedItemPosition),
            )
            val ticket = Ticket(movieId, dateTime, ticketCount.value)
            startActivity(CompletedActivity.getIntent(this, ticket))
        }
    }

    private fun initAdapters() {
        dateSpinner.adapter = dateSpinnerAdapter
        timeSpinner.adapter = timeSpinnerAdapter
    }

    private fun initDateTimes(movie: Movie) {
        val dates: List<LocalDate> =
            ScreeningTimes.getScreeningDates(movie.screeningStartDate, movie.screeningEndDate)
        val times: List<LocalTime> = ScreeningTimes.getScreeningTime(dates[0])
        dateSpinnerAdapter.initItems(dates)
        timeSpinnerAdapter.initItems(times)
    }

    private fun initDateSpinnerSelectedListener(movie: Movie) {
        val dates: List<LocalDate> =
            ScreeningTimes.getScreeningDates(movie.screeningStartDate, movie.screeningEndDate)

        dateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                val times: List<LocalTime> =
                    ScreeningTimes.getScreeningTime(dates[position])
                timeSpinnerAdapter.initItems(times)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    companion object {
        private const val MOVIE_ID = "MOVIE_ID"
        private const val TICKET_COUNT = "TICKET_COUNT"
        private const val DATE_POSITION = "DATE_POSITION"
        private const val TIME_POSITION = "TIME_POSITION"

        fun getIntent(context: Context, movieId: Long): Intent {
            return Intent(context, BookingActivity::class.java).apply {
                putExtra(MOVIE_ID, movieId)
            }
        }
    }
}
