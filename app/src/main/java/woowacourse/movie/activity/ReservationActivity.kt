package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import woowacourse.movie.R
import woowacourse.movie.domain.movie.Movie
import woowacourse.movie.domain.reservation.Reservation
import woowacourse.movie.domain.reservation.TicketCount
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ReservationActivity : AppCompatActivity() {

    private val posterImageView: ImageView by lazy {
        findViewById(R.id.reservation_movie_image_view)
    }
    private val movieNameTextView: TextView by lazy {
        findViewById(R.id.reservation_movie_name_text_view)
    }
    private val screeningDateTextView: TextView by lazy {
        findViewById(R.id.reservation_movie_screening_date_text_view)
    }
    private val runningTimeTextView: TextView by lazy {
        findViewById(R.id.reservation_movie_running_time_text_view)
    }
    private val descriptionTextView: TextView by lazy {
        findViewById(R.id.reservation_movie_description_text_view)
    }
    private val screeningDateSpinner: Spinner by lazy {
        findViewById(R.id.screening_date_spinner)
    }
    private val screeningTimeSpinner: Spinner by lazy {
        findViewById(R.id.screening_time_spinner)
    }
    private val minusButton: Button by lazy {
        findViewById(R.id.reservation_ticket_count_minus_button)
    }
    private val plusButton: Button by lazy {
        findViewById(R.id.reservation_ticket_count_plus_button)
    }
    private val ticketCountTextView: TextView by lazy {
        findViewById(R.id.reservation_ticket_count_text_view)
    }
    private val completeButton: Button by lazy {
        findViewById(R.id.reservation_complete_button)
    }
    private val movie: Movie by lazy {
        intent.getSerializableExtra(getString(R.string.movie_key)) as Movie
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

        val selectedDate: LocalDate = screeningDateSpinner.selectedItem as LocalDate
        val selectedTime: LocalTime = screeningTimeSpinner.selectedItem as LocalTime

        outState.putInt("ticket_count", ticketCountTextView.text.toString().toInt())
        outState.putLong("screening_date", selectedDate.toEpochDay())
        outState.putString("screening_time", selectedTime.toString())
    }

    private fun initReservationView() {
        with(movie) {
            val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")

            posterImage?.let { id -> posterImageView.setImageResource(id) }
            movieNameTextView.text = name.value
            screeningDateTextView.text = getString(R.string.screening_period_form).format(
                movie.screeningPeriod.startDate.format(dateFormat),
                movie.screeningPeriod.endDate.format(dateFormat)
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

        screeningDateSpinner.adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            dates
        )

        screeningDateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (savedInstanceState == null) {
                    initTimeSpinner(dates[position])
                    return
                }
                loadSpinner(savedInstanceState)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                initTimeSpinner(null)
            }
        }
    }

    private fun loadSpinner(savedInstanceState: Bundle) {
        val screeningDate: LocalDate =
            LocalDate.ofEpochDay(savedInstanceState.getLong(getString(R.string.screening_date_key)))
        val screeningTime: LocalTime =
            LocalTime.parse(savedInstanceState.getString(getString(R.string.screening_time_key)))

        val selectedDatePosition: Int =
            movie.screeningPeriod.getScreeningDates().indexOf(screeningDate)
        val selectedTimePosition: Int =
            movie.screeningPeriod.getScreeningTimes(screeningDate).indexOf(screeningTime)

        screeningDateSpinner.setSelection(selectedDatePosition)
        initTimeSpinner(screeningDate, selectedTimePosition)
    }

    private fun initTimeSpinner(date: LocalDate?, defaultPoint: Int = 0) {
        val times = movie.screeningPeriod.getScreeningTimes(date)

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
        plusButton.setOnClickListener {
            val ticketCount = TicketCount(ticketCountTextView.text.toString().toInt() + 1)
            ticketCountTextView.text = ticketCount.value.toString()
        }
    }

    private fun initCompleteButton() {
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
}
