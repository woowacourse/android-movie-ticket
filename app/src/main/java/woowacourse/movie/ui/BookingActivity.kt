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
        initView(movie)
        gatherClickListeners()
        initDateSpinner(movie)
        initTimeSpinner()
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

    private fun initDateSpinner(movie: Movie) {
        val dateSpinner = findViewById<Spinner>(R.id.spinnerScreeningDate)
        val dates: List<LocalDate> =
            ScreeningTimes.getScreeningDates(movie.screeningStartDate, movie.screeningEndDate)
        dateSpinner.adapter = dateSpinnerAdapter
        dateSpinnerAdapter.initItems(dates)

        dateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                val times: List<LocalTime> =
                    ScreeningTimes.getScreeningTime(dates[position]).map { it }
                timeSpinnerAdapter.initItems(times)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun initTimeSpinner() {
        val timeSpinner = findViewById<Spinner>(R.id.spinnerScreeningTime)
        timeSpinner.adapter = timeSpinnerAdapter
    }

    companion object {
        private const val MOVIE_ID = "MOVIE_ID"

        fun getIntent(context: Context, movieId: Long): Intent {
            return Intent(context, BookingActivity::class.java).apply {
                putExtra(MOVIE_ID, movieId)
            }
        }
    }
}
