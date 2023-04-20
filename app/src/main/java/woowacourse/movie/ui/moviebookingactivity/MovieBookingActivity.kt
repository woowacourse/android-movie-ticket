package woowacourse.movie.ui.moviebookingactivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.model.MovieDataState
import woowacourse.movie.model.ScreeningDateTimeState
import woowacourse.movie.ui.DateTimeFormatters
import woowacourse.movie.ui.seatselectionactivity.SeatSelectionActivity
import woowacourse.movie.util.customGetParcelableExtra
import woowacourse.movie.util.setOnSingleClickListener
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.properties.Delegates

class MovieBookingActivity : AppCompatActivity() {

    lateinit var movieDataState: MovieDataState
    lateinit var tvTicketCount: TextView
    lateinit var dateSpinner: Spinner
    lateinit var timeSpinner: Spinner
    lateinit var dateSpinnerAdapter: DateSpinnerAdapter
    lateinit var timeSpinnerAdapter: TimeSpinnerAdapter
    var timeSpinnerRecoverState: Int = -1

    var ticketCount by Delegates.observable(0) { _, _, new ->
        tvTicketCount.text = new.toString()
    }

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
        outState.putInt("ticketCount", ticketCount)
        outState.putInt("selectedTimePosition", timeSpinner.selectedItemPosition)
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
                movieDataState.screeningDay,
                timeSpinnerRecoverState,
                this
            ).apply { initAdapter() }
        dateSpinnerAdapter =
            DateSpinnerAdapter(
                dateSpinner,
                movieDataState.screeningDay,
                this
            ).apply {
                initAdapter()
                setOnItemSelectedListener()
            }
    }

    private fun initExtraData() {
        movieDataState =
            intent.customGetParcelableExtra<MovieDataState>(MOVIE_DATA)
                ?: return finishActivity(MOVIE_DATA)
    }

    private fun setOnItemSelectedListener() {
        dateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                timeSpinnerAdapter.updateTimeTable(dateSpinner.selectedItem as LocalDate)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }

    private fun finishActivity(key: String) {
        Log.d(MOVIE_BOOKING, DATA_NOT_FOUNT_ERROR_MSG.format(key))
        finish()
    }

    private fun initMovieInformation() {
        val ivBookingPoster = findViewById<ImageView>(R.id.iv_booking_poster)
        val tvBookingMovieName = findViewById<TextView>(R.id.tv_booking_movie_name)
        val tvBookingScreeningDay = findViewById<TextView>(R.id.tv_booking_screening_day)
        val tvBookingRunningTime = findViewById<TextView>(R.id.tv_booking_running_time)
        val tvBookingDescription = findViewById<TextView>(R.id.tv_booking_description)

        ivBookingPoster.setImageResource(movieDataState.posterImage)
        tvBookingMovieName.text = movieDataState.title
        tvBookingScreeningDay.text = getString(R.string.screening_date_format)
            .format(
                movieDataState.screeningDay.start.format(DateTimeFormatters.hyphenDateFormatter),
                movieDataState.screeningDay.end.format(DateTimeFormatters.hyphenDateFormatter)
            )
        tvBookingRunningTime.text =
            getString(R.string.running_time_format).format(movieDataState.runningTime)
        tvBookingDescription.text = movieDataState.description
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
        }
    }

    private fun initMinusButtonClickListener() {
        findViewById<Button>(R.id.btn_ticket_minus).setOnSingleClickListener {
            ticketCount--
            if (ticketCount <= MINIMUM_TICKET_COUNT) {
                ticketCount = MINIMUM_TICKET_COUNT
            }
        }
    }

    private fun getScreeningDateTime(): ScreeningDateTimeState {
        val date = dateSpinner.selectedItem as LocalDate
        val time = timeSpinner.selectedItem as LocalTime

        return ScreeningDateTimeState(LocalDateTime.of(date, time), movieDataState.screeningDay)
    }

    private fun initBookingCompleteButtonClickListener() {
        findViewById<Button>(R.id.btn_booking_complete).setOnSingleClickListener {
            val intent = Intent(this, SeatSelectionActivity::class.java).apply {
                putExtra(MOVIE_DATA, movieDataState)
                putExtra(TICKET_COUNT, ticketCount)
                putExtra(BOOKED_SCREENING_DATE_TIME, getScreeningDateTime())
            }
            startActivity(intent)
        }
    }

    companion object {
        private const val MOVIE_DATA = "movieData"
        private const val MINIMUM_TICKET_COUNT = 0
        private const val TICKET_COUNT = "ticketCount"
        private const val BOOKED_SCREENING_DATE_TIME = "bookedScreeningDateTime"
        private const val SELECTED_TIME_POSITION = "selectedTimePosition"
        private const val MOVIE_BOOKING = "MovieBooking"
        private const val DATA_NOT_FOUNT_ERROR_MSG = "%s를 찾을 수 없습니다."
    }
}
