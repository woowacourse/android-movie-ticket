package woowacourse.movie.activity

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import domain.movie.ScreeningDate
import domain.reservation.TicketCount
import woowacourse.movie.R
import woowacourse.movie.activity.MoviesActivity.Companion.MOVIE_KEY
import woowacourse.movie.model.ActivityMovieModel
import woowacourse.movie.model.ActivityReservationModel

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
    private val movie: ActivityMovieModel by lazy {
        intent.customGetSerializable(MOVIE_KEY) as ActivityMovieModel?
            ?: throw IllegalArgumentException(getString(R.string.movie_data_error_message))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        initReservationView()
        initTicketCount(savedInstanceState)
        initTicketCountButton()
        initSpinner(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val selectedDatePosition: Int = screeningDateSpinner.selectedItemPosition
        val selectedTimePosition: Int = screeningTimeSpinner.selectedItemPosition

        outState.putInt(TICKET_COUNT_KEY, ticketCountTextView.text.toString().toInt())
        outState.putInt(SCREENING_DATE_POSITION_KEY, selectedDatePosition)
        outState.putInt(SCREENING_TIME_POSITION_KEY, selectedTimePosition)
    }

    private fun initReservationView() {
        val reservationViewConfiguration = ReservationViewInitializer(
            descriptionTextView = findViewById(R.id.reservation_movie_description_text_view),
            runningTimeTextView = findViewById(R.id.reservation_movie_running_time_text_view),
            screeningDateTextView = findViewById(R.id.reservation_movie_screening_date_text_view),
            movieNameTextView = findViewById(R.id.reservation_movie_name_text_view),
            posterImageView = findViewById(R.id.reservation_movie_image_view),
        )

        reservationViewConfiguration.init(
            movie = movie,
            runningTimeFormat = getString(R.string.running_time_form),
            screeningPeriodFormat = getString(R.string.screening_period_form)
        )
    }

    private fun initTicketCount(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            ticketCountTextView.text = TicketCount.MINIMUM.toString()
            return
        }
        val ticketCount: Int = savedInstanceState.getInt(TICKET_COUNT_KEY)
        ticketCountTextView.text = ticketCount.toString()
    }

    private fun initSpinner(savedInstanceState: Bundle?) {
        val dates = movie.screeningPeriod
        val defaultScreeningDatePosition =
            savedInstanceState?.getInt(SCREENING_DATE_POSITION_KEY) ?: 0
        val defaultScreeningTimePosition =
            savedInstanceState?.getInt(SCREENING_TIME_POSITION_KEY) ?: 0

        screeningDateSpinner.applyArrayAdapter(dates)
        screeningDateSpinner.onItemSelectedListener = SpinnerItemSelectedListener(
            defaultScreeningTimePosition,
            ::onScreeningDateSelected
        )
        screeningDateSpinner.setSelection(defaultScreeningDatePosition)
    }

    private fun onScreeningDateSelected(date: ScreeningDate?, defaultPosition: Int = 0) {
        val times = date?.screeningTimes ?: listOf()

        screeningTimeSpinner.applyArrayAdapter(times)
        screeningTimeSpinner.setSelection(defaultPosition)
    }

    private fun <T> Spinner.applyArrayAdapter(inputData: List<T>) {
        this.adapter = ArrayAdapter(
            context,
            android.R.layout.simple_spinner_item,
            inputData
        )
    }

    private fun initTicketCountButton() {
        val ticketCountButtonInitializer = TicketCountButtonInitializer(
            minusButton = findViewById(R.id.reservation_ticket_count_minus_button),
            plusButton = findViewById(R.id.reservation_ticket_count_plus_button),
            completeButton = findViewById(R.id.reservation_complete_button),
            ticketCountTextView = ticketCountTextView
        )

        ticketCountButtonInitializer.setOnMinusButtonClicked { alertTicketCountError() }
        ticketCountButtonInitializer.setOnPlusButtonClicked()
        ticketCountButtonInitializer.setOnCompletedButtonClicked(
            movie = movie,
            screeningDateSpinner = screeningDateSpinner,
            screeningTimeSpinner = screeningTimeSpinner,
            onCompleted = ::reservationComplete
        )
    }

    private fun alertTicketCountError(
        errorMessage: String = getString(R.string.ticket_count_condition_message_form).format(
            TicketCount.MINIMUM
        )
    ) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }

    private fun reservationComplete(reservation: ActivityReservationModel) {
        val intent = Intent(this, ReservationResultActivity::class.java)

        intent.putExtra(RESERVATION_KEY, reservation)
        startActivity(intent)
        finish()
    }

    companion object {
        private const val TICKET_COUNT_KEY = "ticket_key"
        private const val SCREENING_DATE_POSITION_KEY = "screening_date_key"
        const val SCREENING_TIME_POSITION_KEY = "screening_time_key"
        const val RESERVATION_KEY = "reservation_key"
    }
}
