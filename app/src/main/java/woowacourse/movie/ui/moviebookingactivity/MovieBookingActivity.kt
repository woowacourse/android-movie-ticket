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
import movie.domain.Count
import woowacourse.movie.R
import woowacourse.movie.model.MovieDataState
import woowacourse.movie.model.ScreeningDateTimeState
import woowacourse.movie.model.mapper.toPresentation
import woowacourse.movie.ui.DateTimeFormatters
import woowacourse.movie.ui.SeatSelectionActivity
import woowacourse.movie.util.customGetParcelableExtra
import woowacourse.movie.util.setOnSingleClickListener
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import kotlin.properties.Delegates

class MovieBookingActivity : AppCompatActivity() {

    private lateinit var btnPlus: Button
    private lateinit var btnMinus: Button
    private lateinit var movieDataState: MovieDataState
    private lateinit var tvTicketCount: TextView
    lateinit var dateSpinner: Spinner
    private lateinit var timeSpinner: Spinner
    private lateinit var dateSpinnerAdapter: DateSpinnerAdapter
    lateinit var timeSpinnerAdapter: TimeSpinnerAdapter
    private var timeSpinnerRecoverState: Int = -1
    private var ticketCount: Count by Delegates.observable(Count(1)) { _, _, new ->
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
        initButtonClickListener()
        initBookingCompleteButtonClickListener()
        initSpinnerAdapter()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt("ticketCount", ticketCount.value)
        outState.putInt("selectedTimePosition", timeSpinner.selectedItemPosition)
        super.onSaveInstanceState(outState)
    }

    private fun recoverState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            ticketCount = Count(savedInstanceState.getInt(TICKET_COUNT))
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
                ?: return notFoundData(MOVIE_DATA)
    }

    private fun notFoundData(key: String) {
        Log.d(MOVIE_BOOKING, DATA_NOT_FOUNT_ERROR_MSG.format(key))
        finish()
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
        btnPlus = findViewById<Button>(R.id.btn_ticket_plus)
        btnMinus = findViewById<Button>(R.id.btn_ticket_minus)
    }

    private fun initButtonClickListener() {
        btnPlus.setOnSingleClickListener {
            ticketCount++
        }
        btnMinus.setOnSingleClickListener {
            ticketCount--
            if (ticketCount == Count(0)) {
                ticketCount = Count(1)
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
                putExtra(TICKET_COUNT, ticketCount.toPresentation())
                putExtra(SELECTED_SCREENING_DATE_TIME, getScreeningDateTime())
            }
            startActivity(intent)
        }
    }

    companion object {
        private const val MOVIE_DATA = "movieData"
        private const val TICKET_COUNT = "ticketCount"
        private const val SELECTED_SCREENING_DATE_TIME = "selectedScreeningDateTime"
        private const val SELECTED_TIME_POSITION = "selectedTimePosition"
        private const val MOVIE_BOOKING = "MovieBooking"
        private const val DATA_NOT_FOUNT_ERROR_MSG = "%s를 찾을 수 없습니다."
    }
}
