package woowacourse.movie.ui.moviebookingactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.datetime.ScreeningDateTime
import woowacourse.movie.domain.datetime.ScreeningPeriod
import woowacourse.movie.domain.movie.MovieData
import woowacourse.movie.ui.DateTimeFormatters
import woowacourse.movie.ui.MovieBookingCheckActivity
import woowacourse.movie.util.customGetParcelableExtra
import woowacourse.movie.util.setOnSingleClickListener
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class MovieBookingActivity : AppCompatActivity() {

    lateinit var movieData: MovieData
    lateinit var tvTicketCount: TextView
    lateinit var dateSpinner: Spinner
    lateinit var timeSpinner: Spinner
    lateinit var dateSpinnerAdapter: DateSpinnerAdapter
    lateinit var timeSpinnerAdapter: TimeSpinnerAdapter
    var timeSpinnerRecoverState: Int = -1
    private var ticketCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_booking)

        initTicketCountView()
        initSpinners()
        recoverState(savedInstanceState)
        initExtraData()
        initMovieInformation()
        initTicketCount()
        initMinusButtonClickListener()
        initPlusButtonClickListener()
        initBookingCompleteButtonClickListener()
        initSpinnerAdapter()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(TICKET_COUNT, ticketCount)
        outState.putInt(SELECTED_TIME_POSITION, timeSpinner.selectedItemPosition)
        super.onSaveInstanceState(outState)
    }

    private fun recoverState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            ticketCount = savedInstanceState.getInt(TICKET_COUNT)
            timeSpinnerRecoverState = savedInstanceState.getInt(SELECTED_TIME_POSITION)
        }
    }

    private fun initSpinners() {
        dateSpinner = findViewById(R.id.spinner_date)
        timeSpinner = findViewById(R.id.spinner_time)
    }

    private fun initSpinnerAdapter() {
        timeSpinnerAdapter =
            TimeSpinnerAdapter(
                timeSpinner,
                movieData.screeningDay,
                timeSpinnerRecoverState,
                this
            ).apply { initAdapter() }
        dateSpinnerAdapter =
            DateSpinnerAdapter(
                dateSpinner,
                timeSpinnerAdapter::updateTimeTable,
                movieData.screeningDay,
                this
            )
                .apply { initAdapter() }
    }

    private fun initExtraData() {
        movieData = intent.customGetParcelableExtra<MovieData>(MOVIE_DATA) ?: run {
            finish()
            MovieData(
                R.drawable.img_error,
                "-1",
                ScreeningPeriod(LocalDate.parse("9999-12-30"), LocalDate.parse("9999-12-31")),
                -1
            )
        }
    }

    private fun initMovieInformation() {
        val ivBookingPoster = findViewById<ImageView>(R.id.iv_booking_poster)
        val tvBookingMovieName = findViewById<TextView>(R.id.tv_booking_movie_name)
        val tvBookingScreeningDay = findViewById<TextView>(R.id.tv_booking_screening_day)
        val tvBookingRunningTime = findViewById<TextView>(R.id.tv_booking_running_time)
        val tvBookingDescription = findViewById<TextView>(R.id.tv_booking_description)

        ivBookingPoster.setImageResource(movieData.posterImage)
        tvBookingMovieName.text = movieData.title
        tvBookingScreeningDay.text = this.getString(R.string.screening_date_format)
            .format(
                movieData.screeningDay.start.format(DateTimeFormatters.hyphenDateFormatter),
                movieData.screeningDay.end.format(DateTimeFormatters.hyphenDateFormatter)
            )
        tvBookingRunningTime.text =
            this.getString(R.string.running_time_format).format(movieData.runningTime)
        tvBookingDescription.text = movieData.description
    }

    private fun initTicketCount() {
        tvTicketCount.text = ticketCount.toString()
    }

    private fun initTicketCountView() {
        tvTicketCount = findViewById(R.id.tv_ticket_count)
    }

    private fun initPlusButtonClickListener() {
        findViewById<Button>(R.id.btn_ticket_plus).setOnSingleClickListener {
            ticketCount++
            tvTicketCount.text = ticketCount.toString()
        }
    }

    private fun initMinusButtonClickListener() {
        findViewById<Button>(R.id.btn_ticket_minus).setOnSingleClickListener {
            ticketCount--
            if (ticketCount <= 1) ticketCount = 1
            tvTicketCount.text = ticketCount.toString()
        }
    }

    private fun getScreeningDateTime(): ScreeningDateTime {
        val date = dateSpinner.selectedItem as LocalDate
        val time = timeSpinner.selectedItem as LocalTime

        return ScreeningDateTime(LocalDateTime.of(date, time), movieData.screeningDay)
    }

    // timespinner 초기화 관련 방어코드 고려
    private fun initBookingCompleteButtonClickListener() {
        findViewById<Button>(R.id.btn_booking_complete).setOnSingleClickListener {
            val intent = Intent(this, MovieBookingCheckActivity::class.java).apply {
                putExtra(MovieBookingCheckActivity.MOVIE_DATA, movieData)
                putExtra(MovieBookingCheckActivity.TICKET_COUNT, ticketCount)
                putExtra(
                    MovieBookingCheckActivity.BOOKED_SCREENING_DATE_TIME,
                    getScreeningDateTime()
                )
            }
            startActivity(intent)
        }
    }

    companion object {
        // savedInstanceState
        const val TICKET_COUNT = "ticketCount"
        const val SELECTED_TIME_POSITION = "selectedTimePosition"

        // intent data
        const val MOVIE_DATA = "movieData"
    }
}
