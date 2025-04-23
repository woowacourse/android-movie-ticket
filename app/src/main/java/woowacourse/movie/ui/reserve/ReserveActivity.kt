package woowacourse.movie.ui.reserve

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
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.domain.date.scheduler.CurrentDateScheduler
import woowacourse.movie.domain.date.scheduler.CurrentTimeScheduler
import woowacourse.movie.domain.date.scheduler.ReservationScheduler
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.Reservation
import woowacourse.movie.domain.model.ScreeningDate
import woowacourse.movie.domain.model.Ticket
import woowacourse.movie.domain.model.TicketType
import woowacourse.movie.domain.model.Tickets
import woowacourse.movie.ui.extensions.serializableData
import woowacourse.movie.ui.factory.CustomAlertDialog
import woowacourse.movie.ui.factory.DialogInfo
import woowacourse.movie.ui.reservationResult.ReservationResultActivity
import woowacourse.movie.ui.reservationResult.ReservationResultActivity.Companion.KEY_RESERVATION_RESULT_ACTIVITY_RESERVATION
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ReserveActivity : AppCompatActivity() {
    private val dateSpinner: Spinner by lazy { findViewById(R.id.sp_date) }
    private val timeSpinner: Spinner by lazy { findViewById(R.id.sp_time) }
    private val ticketCountTextView: TextView by lazy { findViewById(R.id.tv_ticket_count) }
    private val reservationScheduler =
        ReservationScheduler(CurrentDateScheduler(), CurrentTimeScheduler())
    private val customAlertDialog = CustomAlertDialog(this)
    private lateinit var reservation: Reservation
    private var isDateInit = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserve)
        initSystemUI()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initWithMovie(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY_RESERVATION_RESULT_ACTIVITY_RESERVATION, reservation)
        outState.putInt(KEY_DATE_POSITION, dateSpinner.selectedItemPosition)
        outState.putInt(KEY_TIME_POSITION, timeSpinner.selectedItemPosition)
        outState.putSerializable(KEY_SELECTED_DATE, dateSpinner.selectedItem as LocalDate)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        val datePosition = savedInstanceState.getInt(KEY_DATE_POSITION)
        val timePosition = savedInstanceState.getInt(KEY_TIME_POSITION)
        val selectedDate =
            savedInstanceState.serializableData(KEY_SELECTED_DATE, LocalDate::class.java)
        dateSpinner.setSelection(datePosition)
        if (selectedDate != null) updateTimeSpinner(selectedDate)
        timeSpinner.setSelection(timePosition)
        isDateInit = true
    }

    private fun initSystemUI() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initWithMovie(savedInstanceState: Bundle?) {
        val movie = movie()
        if (movie == null) {
            showMissingMovieDialog()
        } else {
            initMovieContent(movie)
            initReservation(savedInstanceState, movie)
        }
    }

    private fun movie(): Movie? = intent.serializableData(KEY_RESERVE_ACTIVITY_MOVIE, Movie::class.java)

    private fun showMissingMovieDialog() {
        val dialogInfo =
            DialogInfo(
                getString(R.string.empty_movie_title),
                getString(R.string.empty_movie_message),
                getString(R.string.confirm),
                null,
                { },
                ::finish,
            )
        customAlertDialog.show(dialogInfo)
    }

    private fun initMovieContent(movie: Movie) {
        with(movie) {
            findViewById<ImageView>(R.id.iv_poster).setImageResource(imageUrl)
            findViewById<TextView>(R.id.tv_title).text = title
            findViewById<TextView>(R.id.tv_screening_date).text = formatScreeningDate(screeningDate)
            findViewById<TextView>(R.id.tv_running_time).text =
                getString(R.string.formatted_minute, runningTime.time)
        }
    }

    private fun initReservation(
        savedInstanceState: Bundle?,
        movie: Movie,
    ) {
        val timePosition = savedInstanceState?.getInt(KEY_TIME_POSITION) ?: 0
        initDateSpinner(movie.screeningDate)
        initTimeSpinner(movie.screeningDate.startDate)
        this.reservation =
            savedInstanceState?.serializableData(KEY_RESERVATION_RESULT_ACTIVITY_RESERVATION, Reservation::class.java)
                ?: Reservation(
                    movie.title, getSelectedDateTime(),
                    Tickets(listOf(Ticket(TicketType.DEFAULT))),
                )
        updateTicketCount()
        initButtonListeners()
        timeSpinner.setSelection(timePosition)
    }

    private fun initDateSpinner(screeningDate: ScreeningDate) {
        val dates = reservationScheduler.reservableDates(screeningDate, LocalDate.now())

        dateSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dates)
        dateSpinner.onItemSelectedListener = createDateSelectionListener(dates)
    }

    private fun initTimeSpinner(startDate: LocalDate) {
        updateTimeSpinner(reservationScheduler.startDate(startDate, LocalDate.now()))
        timeSpinner.onItemSelectedListener = createTimeSelectionListener()
    }

    private fun updateTimeSpinner(selectedDate: LocalDate) {
        val times =
            reservationScheduler.reservableTimes(
                selectedDate,
                LocalDateTime.now(),
            )

        timeSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, times)
    }

    private fun getSelectedDateTime(): LocalDateTime =
        LocalDateTime.of(
            dateSpinner.selectedItem as LocalDate,
            timeSpinner.selectedItem as LocalTime,
        )

    private fun initButtonListeners() {
        minusButtonInit()
        plusButtonInit()
        reserveButtonInit()
    }

    private fun minusButtonInit() {
        findViewById<Button>(R.id.btn_minus).setOnClickListener {
            if (reservation.canMinus()) {
                reservation = reservation.minusCount()
                updateTicketCount()
            } else {
                Toast.makeText(this, R.string.validate_min_movie_ticket, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun plusButtonInit() {
        findViewById<Button>(R.id.btn_plus).setOnClickListener {
            reservation = reservation.plusCount()
            updateTicketCount()
        }
    }

    private fun reserveButtonInit() {
        val dialogInfo =
            DialogInfo(
                getString(R.string.reserve_dialog_title),
                getString(R.string.reserve_dialog_message),
                getString(R.string.reserve_dialog_positive_button),
                getString(R.string.cancel),
                ::moveToReservationResult,
            )

        findViewById<Button>(R.id.btn_reserve).setOnClickListener {
            customAlertDialog.show(dialogInfo)
        }
    }

    private fun moveToReservationResult() {
        if (::reservation.isInitialized) {
            startActivity(
                Intent(this, ReservationResultActivity::class.java).apply {
                    putExtra(KEY_RESERVATION_RESULT_ACTIVITY_RESERVATION, reservation)
                },
            )
        } else {
            val dialogInfo =
                DialogInfo(
                    getString(R.string.error_reservation_title),
                    getString(R.string.error_reservation_message),
                    getString(R.string.confirm),
                    null,
                    ::finish,
                )
            customAlertDialog.show(dialogInfo)
        }
    }

    private fun updateTicketCount() {
        ticketCountTextView.text = reservation.ticketCount.toString()
    }

    private fun formatScreeningDate(screeningDate: ScreeningDate): String {
        val formatter = DateTimeFormatter.ofPattern(getString(R.string.date_format))
        return getString(
            R.string.formatted_screening_date,
            screeningDate.startDate.format(formatter),
            screeningDate.endDate.format(formatter),
        )
    }

    private fun createDateSelectionListener(dates: List<LocalDate>) =
        object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                if (isDateInit) {
                    isDateInit = false
                    return
                }

                val selectedDate = dates[position]
                updateTimeSpinner(selectedDate)
                reservation = reservation.updateReservedTime(getSelectedDateTime())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

    private fun createTimeSelectionListener() =
        object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                reservation = reservation.updateReservedTime(getSelectedDateTime())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

    companion object {
        private const val KEY_DATE_POSITION = "datePosition"
        private const val KEY_TIME_POSITION = "timePosition"
        private const val KEY_SELECTED_DATE = "selectedDate"
        const val KEY_RESERVE_ACTIVITY_MOVIE = "key_reserve_activity_movie"
    }
}
