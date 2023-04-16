package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import domain.movie.Movie
import domain.movie.ScreeningDate
import domain.reservation.Reservation
import domain.reservation.TicketCount
import woowacourse.movie.R
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ReservationActivity : AppCompatActivity() {

    private val screeningDateSpinner: Spinner by lazy {
        findViewById(R.id.screening_date_spinner)
    }
    private val screeningTimeSpinner: Spinner by lazy {
        findViewById(R.id.screening_time_spinner)
    }
    private val ticketCountTextView: TextView by lazy {
        findViewById(R.id.reservation_ticket_count_text_view)
    }
    private val movie: Movie by lazy {
        intent.getSerializableExtra(getString(R.string.movie_key)) as Movie?
            ?: throw IllegalArgumentException(getString(R.string.movie_data_error_message))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        initReservationView()
        initClickListener()
        initTicketCount(savedInstanceState)
        initSpinner(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val selectedDatePosition: Int = screeningDateSpinner.selectedItemPosition
        val selectedTimePosition: Int = screeningTimeSpinner.selectedItemPosition

        outState.putInt(
            getString(R.string.ticket_count_key),
            ticketCountTextView.text.toString().toInt()
        )
        outState.putInt(SCREENING_DATE_POINT_KEY, selectedDatePosition)
        outState.putInt(SCREENING_TIME_POINT_KEY, selectedTimePosition)
    }

    private fun initReservationView() {
        val descriptionTextView: TextView =
            findViewById(R.id.reservation_movie_description_text_view)
        val runningTimeTextView: TextView =
            findViewById(R.id.reservation_movie_running_time_text_view)
        val screeningDateTextView: TextView =
            findViewById(R.id.reservation_movie_screening_date_text_view)
        val movieNameTextView: TextView =
            findViewById(R.id.reservation_movie_name_text_view)
        val posterImageView: ImageView =
            findViewById(R.id.reservation_movie_image_view)

        with(movie) {
            val dateFormat: DateTimeFormatter = DateTimeFormatter.ISO_DATE

            posterImage?.let { id -> posterImageView.setImageResource(id) }
            movieNameTextView.text = name.value
            screeningDateTextView.text = getString(R.string.screening_period_form).format(
                movie.screeningPeriod.startDate.value.format(dateFormat),
                movie.screeningPeriod.endDate.value.format(dateFormat)
            )
            runningTimeTextView.text = getString(R.string.running_time_form).format(runningTime)
            descriptionTextView.text = description
        }
    }

    private fun initTicketCount(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            ticketCountTextView.text = TicketCount.MINIMUM.toString()
            return
        }
        val ticketCount: Int = savedInstanceState.getInt(getString(R.string.ticket_count_key))
        ticketCountTextView.text = ticketCount.toString()
    }

    private fun initSpinner(savedInstanceState: Bundle?) {
        val dates = movie.screeningPeriod.getScreeningDates()
        val defaultPoint = savedInstanceState?.getInt(SCREENING_DATE_POINT_KEY) ?: 0

        screeningDateSpinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            dates.map { it.value }
        )
        screeningDateSpinner.onItemSelectedListener = ScreeningSpinnerOption(
            savedInstanceState,
            screeningDateSpinner,
            ::initTimeSpinner
        )
        screeningDateSpinner.setSelection(defaultPoint)
    }

    private fun initTimeSpinner(date: ScreeningDate?, defaultPoint: Int = 0) {
        val times = date?.screeningTimes ?: listOf()

        screeningTimeSpinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            times
        )
        screeningTimeSpinner.setSelection(defaultPoint)
    }

    private fun initClickListener() {
        initMinusClickListener()
        initPlusClickListener()
        initCompleteButton()
    }

    private fun initMinusClickListener() {
        val minusButton = findViewById<Button>(R.id.reservation_ticket_count_minus_button)

        minusButton.setOnClickListener {
            runCatching {
                val ticketCount = TicketCount(ticketCountTextView.text.toString().toInt() - 1)
                ticketCountTextView.text = ticketCount.value.toString()
            }.onFailure {
                val ticketCountConditionMessage =
                    getString(R.string.ticket_count_condition_message_form).format(TicketCount.MINIMUM)
                Toast.makeText(this, ticketCountConditionMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initPlusClickListener() {
        val plusButton = findViewById<Button>(R.id.reservation_ticket_count_plus_button)

        plusButton.setOnClickListener {
            val ticketCount = TicketCount(ticketCountTextView.text.toString().toInt() + 1)
            ticketCountTextView.text = ticketCount.value.toString()
        }
    }

    private fun initCompleteButton() {
        val completeButton: Button = findViewById(R.id.reservation_complete_button)

        completeButton.setOnClickListener {
            val ticketCount = ticketCountTextView.text.toString().toInt()
            val screeningDate = screeningDateSpinner.selectedItem as LocalDate
            val screeningTime = screeningTimeSpinner.selectedItem as LocalTime
            val reservation: Reservation =
                Reservation.from(movie, ticketCount, LocalDateTime.of(screeningDate, screeningTime))
            val intent = Intent(this, ReservationResultActivity::class.java)
            intent.putExtra(getString(R.string.reservation_key), reservation)
            startActivity(intent)
            finish()
        }
    }

    companion object {
        const val SCREENING_DATE_POINT_KEY = "screening_date_key"
        const val SCREENING_TIME_POINT_KEY = "screening_time_key"
    }
}
