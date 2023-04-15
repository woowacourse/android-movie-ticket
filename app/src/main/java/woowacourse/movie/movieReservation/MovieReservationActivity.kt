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
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import movie.DiscountPolicy
import movie.MovieSchedule
import movie.MovieTicket
import movie.TicketCount
import woowacourse.movie.R
import woowacourse.movie.movieTicket.MovieTicketActivity
import woowacourse.movie.utils.DateUtil
import java.time.LocalDate
import java.time.LocalTime

class MovieReservationActivity : AppCompatActivity() {
    private val movieSchedule by lazy { intent.getSerializableExtra(KEY_MOVIE_SCHEDULE) as MovieSchedule }
    private var ticketCount = TicketCount(1)
    private var selectedPosition = 0

    private val dateSpinner by lazy { findViewById<Spinner>(R.id.reservation_screening_date_spinner) }
    private val timeSpinner by lazy { findViewById<Spinner>(R.id.reservation_screening_time_spinner) }
    private val ticketCountView by lazy { findViewById<TextView>(R.id.reservation_ticket_count) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_reservation)

        updateMovieView()

        registerToolbar()
        registerListener()

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

    private fun updateMovieView() {
        val moviePosterView = findViewById<ImageView>(R.id.reservation_movie_poster)
        val movieTitleView = findViewById<TextView>(R.id.reservation_movie_title)
        val movieReleaseDataView = findViewById<TextView>(R.id.reservation_movie_release_date)
        val movieRunningTimeView = findViewById<TextView>(R.id.reservation_movie_running_time)
        val movieSummaryView = findViewById<TextView>(R.id.reservation_movie_summary)

        val context = this

        moviePosterView.setImageResource(movieSchedule.poster)
        movieTitleView.text = title
        movieReleaseDataView.text = DateUtil(context).getDateRange(movieSchedule.startDate, movieSchedule.endDate)
        movieRunningTimeView.text = getString(R.string.movie_running_time).format(movieSchedule.runningTime)
        movieSummaryView.text = movieSchedule.summary
    }

    private fun registerToolbar() {
        val reservationToolbar = findViewById<Toolbar>(R.id.reservation_toolbar)
        setSupportActionBar(reservationToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun registerListener() {
        registerCountButton()
        registerReservationButton()
        registerSpinnerListener()
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
        val dateList = movieSchedule.getScreeningDate()
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

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun updateTimeView(date: LocalDate) {
        val timeList = movieSchedule.getScreeningTime(date)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, timeList)
        timeSpinner.adapter = adapter
    }

    private fun registerReservationButton() {
        val reservationButton = findViewById<TextView>(R.id.reservation_complete_button)
        reservationButton.setOnClickListener {
            val intent = Intent(this, MovieTicketActivity::class.java)
            val selectedDate = LocalDate.parse(dateSpinner.selectedItem.toString())
            val selectedTime = LocalTime.parse(timeSpinner.selectedItem.toString())
            val discountPolicy = DiscountPolicy.of(selectedDate, selectedTime)

            kotlin.runCatching {
                val movieTicket = MovieTicket(
                    eachPrice = discountPolicy(MOVIE_TICKET_PRICE),
                    count = ticketCount,
                    title = movieSchedule.title,
                    date = selectedDate,
                    time = selectedTime,
                )
                intent.putExtra(MovieTicketActivity.KEY_MOVIE_TICKET, movieTicket)
                ContextCompat.startActivity(this, intent, null)
            }.onFailure {
                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        private const val MOVIE_TICKET_PRICE = 13000
        private const val KEY_COUNT = "count"
        private const val KEY_TIME = "time"
        const val KEY_MOVIE_SCHEDULE = "movieSchedule"
    }
}
