package woowacourse.movie.movieReservation

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import movie.data.TicketCount
import movie.screening.ScreeningTime
import woowacourse.movie.R
import woowacourse.movie.movieSeat.MovieSeatActivity
import woowacourse.movie.uimodel.MovieDetailUi
import woowacourse.movie.uimodel.MovieModelUi
import woowacourse.movie.utils.DateUtil
import woowacourse.movie.utils.toDomain
import java.time.LocalDate
import java.time.LocalTime

class MovieReservationActivity : AppCompatActivity() {

    private val movieScheduleUi by lazy { intent.getSerializableExtra(KEY_MOVIE_SCHEDULE) as MovieModelUi.MovieScheduleUi }
    private var ticketCount = TicketCount(1)
    private var selectedPosition = 0

    private val dateSpinner by lazy { findViewById<Spinner>(R.id.reservation_screening_date_spinner) }
    private val timeSpinner by lazy { findViewById<Spinner>(R.id.reservation_screening_time_spinner) }
    private val ticketCountView by lazy { findViewById<TextView>(R.id.reservation_ticket_count) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation)

        initMovieView()
        initListener()
        initInstanceState(savedInstanceState)
    }

    private fun initInstanceState(savedInstanceState: Bundle?) {
        savedInstanceState?.let {
            ticketCount = TicketCount(it.getInt(KEY_COUNT))
            ticketCountView.text = it.getInt(KEY_COUNT).toString()

            selectedPosition = it.getInt(KEY_TIME)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_COUNT, ticketCount.toInt())

        selectedPosition = timeSpinner.selectedItemPosition
        outState.putInt(KEY_TIME, selectedPosition)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initMovieView() {
        ticketCountView.text = ticketCount.toInt().toString()
        val moviePosterView = findViewById<ImageView>(R.id.reservation_movie_poster)
        val movieTitleView = findViewById<TextView>(R.id.reservation_movie_title)
        val movieReleaseDataView = findViewById<TextView>(R.id.reservation_movie_release_date)
        val movieRunningTimeView = findViewById<TextView>(R.id.reservation_movie_running_time)
        val movieSummaryView = findViewById<TextView>(R.id.reservation_movie_summary)

        val context = this

        with(movieScheduleUi) {
            moviePosterView.setImageResource(poster)
            movieTitleView.text = title
            movieReleaseDataView.text = DateUtil(context).getDateRange(startDate, endDate)
            movieRunningTimeView.text = getString(R.string.movie_running_time).format(runningTime)
            movieSummaryView.text = summary
        }
    }

    private fun initListener() {
        registerCountButton()
        registerReservationButton()
        registerSpinnerListener()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun registerCountButton() {
        val decreaseButton = findViewById<TextView>(R.id.reservation_decrease_ticket_button)
        val increaseButton = findViewById<TextView>(R.id.reservation_increase_ticket_button)

        decreaseButton.setOnClickListener {
            ticketCountView.text = (--ticketCount).toString()
        }
        increaseButton.setOnClickListener {
            ticketCountView.text = (++ticketCount).toString()
        }
    }

    private fun registerSpinnerListener() {
        val dateList = movieScheduleUi.toDomain().getScreeningDate()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dateList)
        dateSpinner.adapter = adapter

        dateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                updateTimeView(LocalDate.parse(dateList[position]))
                timeSpinner.setSelection(selectedPosition)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) = Unit
        }
    }

    private fun updateTimeView(date: LocalDate) {
        val timeList = ScreeningTime.getScreeningTime(date)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, timeList)
        timeSpinner.adapter = adapter
    }

    private fun registerReservationButton() {
        val reservationButton = findViewById<TextView>(R.id.reservation_complete_button)
        reservationButton.setOnClickListener { startActivity(makeIntent()) }
    }

    private fun makeIntent(): Intent {
        val intent = Intent(this, MovieSeatActivity::class.java)
        val movieDetailUi = makeMovieDetailUi()
        intent.putExtra(MovieSeatActivity.KEY_MOVIE_DETAIL, movieDetailUi)
        return intent
    }

    private fun makeMovieDetailUi(): MovieDetailUi {
        val selectedDate = LocalDate.parse(dateSpinner.selectedItem.toString())
        val selectedTime = LocalTime.parse(timeSpinner.selectedItem.toString())

        return MovieDetailUi(
            title = movieScheduleUi.title,
            count = ticketCount,
            date = selectedDate,
            time = selectedTime,
        )
    }

    companion object {
        private const val KEY_COUNT = "count"
        private const val KEY_TIME = "time"
        const val KEY_MOVIE_SCHEDULE = "movieScheduleUi"
    }
}
