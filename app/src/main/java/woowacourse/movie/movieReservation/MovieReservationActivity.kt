package woowacourse.movie.movieReservation

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import entity.Screening
import movie.ScreeningDate
import movie.TicketCount
import woowacourse.movie.R
import woowacourse.movie.movieTicket.MovieTicketActivity
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class MovieReservationActivity : AppCompatActivity() {
    private val screening by lazy { intent.getSerializableExtra(KEY_MOVIE_Screening) as? Screening ?: throw IllegalArgumentException() }
    private var ticketCount = TicketCount(1)
    private var selectedPosition = 0

    private val dateSpinner by lazy { findViewById<Spinner>(R.id.reservation_screening_date_spinner) }
    private val timeSpinner by lazy { findViewById<Spinner>(R.id.reservation_screening_time_spinner) }
    private val ticketCountView by lazy { findViewById<TextView>(R.id.reservation_ticket_count) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation)

        initMovieView()
        initToolbar()
        initListener()

        updateInstanceState(savedInstanceState)
    }

    private fun updateInstanceState(savedInstanceState: Bundle?) {
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
        MovieReservationContents(
            posterView = findViewById(R.id.reservation_movie_poster),
            titleView = findViewById(R.id.reservation_movie_title),
            releaseDataView = findViewById(R.id.reservation_movie_release_date),
            runningTimeView = findViewById(R.id.reservation_movie_running_time),
            summaryView = findViewById(R.id.reservation_movie_summary),
        ).bind(this, screening)
    }

    private fun initToolbar() {
        val reservationToolbar = findViewById<Toolbar>(R.id.reservation_toolbar)
        setSupportActionBar(reservationToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun initListener() {
        initCountButton()
        initReservationButton()
        initDateSpinnerListener()
    }

    private fun initCountButton() {
        val decreaseButton = findViewById<TextView>(R.id.reservation_decrease_ticket_button)
        val increaseButton = findViewById<TextView>(R.id.reservation_increase_ticket_button)

        decreaseButton.setOnClickListener {
            ticketCountView.text = (--ticketCount).toString()
        }
        increaseButton.setOnClickListener {
            ticketCountView.text = (++ticketCount).toString()
        }
    }

    private fun initDateSpinnerListener() {
        val dateList = ScreeningDate.getScreeningDate(screening.startDate, screening.endDate)
        dateSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dateList)
        dateSpinner.onItemSelectedListener = DateSpinnerListener()
    }

    inner class DateSpinnerListener : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            initTimeView(LocalDate.parse(dateSpinner.selectedItem.toString()))
            timeSpinner.setSelection(selectedPosition)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) = Unit
    }

    private fun initTimeView(date: LocalDate) {
        val timeList = ScreeningDate.getScreeningTime(date)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, timeList)
        timeSpinner.adapter = adapter
    }

    private fun initReservationButton() {
        val reservationButton = findViewById<TextView>(R.id.reservation_complete_button)
        reservationButton.setOnClickListener {
            runCatching { onReservationButtonClicked() }
                .onFailure { Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show() }
        }
    }

    private fun onReservationButtonClicked() {
        val movieTicket = screening.reserve(ticketCount, getSelectedDateTime())
        startActivity(
            Intent(this, MovieTicketActivity::class.java).apply {
                putExtra(MovieTicketActivity.KEY_MOVIE_TICKET, movieTicket)
            },
            null,
        )
    }

    private fun getSelectedDateTime(): LocalDateTime = LocalDateTime.of(
        LocalDate.parse(dateSpinner.selectedItem.toString()),
        LocalTime.parse(timeSpinner.selectedItem.toString()),
    )

    companion object {
        private const val KEY_COUNT = "count"
        private const val KEY_TIME = "time"
        const val KEY_MOVIE_Screening = "movieScreening"
    }
}
