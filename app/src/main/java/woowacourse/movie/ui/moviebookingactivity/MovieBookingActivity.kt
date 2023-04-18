package woowacourse.movie.ui.moviebookingactivity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import woowacourse.movie.R
import woowacourse.movie.domain.datetime.ScreeningDateTime
import woowacourse.movie.domain.price.TicketCount
import woowacourse.movie.ui.MovieBookingCheckActivity
import woowacourse.movie.ui.model.MovieUIModel
import woowacourse.movie.util.customGetSerializableExtra
import woowacourse.movie.util.setOnSingleClickListener
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class MovieBookingActivity : AppCompatActivity() {

    lateinit var movieData: MovieUIModel
    lateinit var tvTicketCount: TextView
    lateinit var dateSpinner: Spinner
    lateinit var timeSpinner: Spinner
    lateinit var dateSpinnerAdapter: DateSpinnerAdapter
    lateinit var timeSpinnerAdapter: TimeSpinnerAdapter
    var timeSpinnerRecoverState: Int = -1
    private val ticketCount = TicketCount(1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_booking)

        initTicketCountView()
        initSpinners()
        recoverState(savedInstanceState)
        initExtraData()
        initMovieInformationView()
        initTicketCount()
        initMinusButtonClickListener()
        initPlusButtonClickListener()
        initBookingCompleteButtonClickListener()
        initSpinnerAdapter()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(TICKET_COUNT, ticketCount.value)
        outState.putInt(SELECTED_TIME_POSITION, timeSpinner.selectedItemPosition)
        super.onSaveInstanceState(outState)
    }

    private fun recoverState(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            ticketCount.value = savedInstanceState.getInt(TICKET_COUNT)
            timeSpinnerRecoverState = savedInstanceState.getInt(SELECTED_TIME_POSITION)
        }
    }

    private fun initMovieInformationView() {
        MovieInformationView(
            findViewById<ConstraintLayout>(R.id.layout_movie_information),
            movieData
        )
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
            )
        dateSpinnerAdapter =
            DateSpinnerAdapter(
                dateSpinner,
                timeSpinnerAdapter::updateTimeTable,
                movieData.screeningDay,
                this
            )
    }

    private fun initExtraData() {
        movieData = intent.customGetSerializableExtra(MOVIE_DATA) ?: throw IllegalStateException(
            INTENT_EXTRA_INITIAL_ERROR
        )
    }

    private fun initTicketCount() {
        tvTicketCount.text = ticketCount.value.toString()
    }

    private fun initTicketCountView() {
        tvTicketCount = findViewById(R.id.tv_ticket_count)
    }

    private fun initPlusButtonClickListener() {
        findViewById<Button>(R.id.btn_ticket_plus).setOnSingleClickListener {
            ticketCount.value++
            tvTicketCount.text = ticketCount.value.toString()
        }
    }

    private fun initMinusButtonClickListener() {
        findViewById<Button>(R.id.btn_ticket_minus).setOnSingleClickListener {
            ticketCount.value--
            if (ticketCount.value <= 1) ticketCount.value = 1
            tvTicketCount.text = ticketCount.value.toString()
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

        private const val INTENT_EXTRA_INITIAL_ERROR = "intent 의 데이터 이동시 data가 null으로 넘어오고 있습니다"
    }
}
