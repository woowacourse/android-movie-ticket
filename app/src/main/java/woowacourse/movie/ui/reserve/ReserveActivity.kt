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
import woowacourse.movie.domain.model.PurchaseCount
import woowacourse.movie.domain.model.Reservation
import woowacourse.movie.domain.model.ScreeningDate
import woowacourse.movie.domain.model.TicketMachine
import woowacourse.movie.domain.model.TicketType
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
    private lateinit var dateSpinner: Spinner
    private lateinit var timeSpinner: Spinner
    private lateinit var ticketCountTextView: TextView
    private lateinit var posterView: ImageView
    private lateinit var titleView: TextView
    private lateinit var screeningDateView: TextView
    private lateinit var runningTimeView: TextView
    private val reservationScheduler =
        ReservationScheduler(CurrentDateScheduler(), CurrentTimeScheduler())
    private lateinit var minusButton: Button
    private lateinit var plusButton: Button
    private lateinit var reserveButton: Button
    private val customAlertDialog = CustomAlertDialog(this)
    private lateinit var reservation: Reservation
    private var purchaseCount = PurchaseCount(1)
    private val ticketMachine = TicketMachine()
    private var savedTimePosition = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserve)
        initSystemUI()
        initViewId()
        initButtonListeners()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        initWithMovie()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable(KEY_RESERVATION_RESULT_ACTIVITY_RESERVATION, reservation)
        outState.putInt(KEY_DATE_POSITION, dateSpinner.selectedItemPosition)
        outState.putInt(KEY_TIME_POSITION, timeSpinner.selectedItemPosition)
        outState.putInt(KEY_PURCHASE_COUNT, purchaseCount.value)
        outState.putSerializable(KEY_SELECTED_DATE, dateSpinner.selectedItem as LocalDate)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val datePosition = savedInstanceState.getInt(KEY_DATE_POSITION)
        val timePosition = savedInstanceState.getInt(KEY_TIME_POSITION)
        val selectedDate =
            savedInstanceState.serializableData(KEY_SELECTED_DATE, LocalDate::class.java)
        val purchaseCount = savedInstanceState.getInt(KEY_PURCHASE_COUNT)
        this.purchaseCount = PurchaseCount(purchaseCount)
        dateSpinner.setSelection(datePosition)
        if (selectedDate != null) updateTimeSpinner(selectedDate)
        ticketCountTextView.text = purchaseCount.toString()
        this.savedTimePosition = timePosition
    }

    private fun initSystemUI() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun initViewId() {
        dateSpinner = findViewById(R.id.sp_date)
        timeSpinner = findViewById(R.id.sp_time)
        ticketCountTextView = findViewById(R.id.tv_ticket_count)
        posterView = findViewById(R.id.iv_poster)
        titleView = findViewById(R.id.tv_title)
        screeningDateView = findViewById(R.id.tv_screening_date)
        runningTimeView = findViewById(R.id.tv_running_time)
        minusButton = findViewById(R.id.btn_minus)
        plusButton = findViewById(R.id.btn_plus)
        reserveButton = findViewById(R.id.btn_reserve)
    }

    private fun initButtonListeners() {
        minusButtonInit()
        plusButtonInit()
        reserveButtonInit()
    }

    private fun minusButtonInit() {
        minusButton.setOnClickListener {
            if (purchaseCount.canMinus()) {
                purchaseCount = purchaseCount.decrease()
                updateTicketCount()
            } else {
                Toast.makeText(this, R.string.validate_min_movie_ticket, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun plusButtonInit() {
        plusButton.setOnClickListener {
            purchaseCount = purchaseCount.increase()
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

        reserveButton.setOnClickListener {
            customAlertDialog.show(dialogInfo)
        }
    }

    private fun moveToReservationResult() {
        if (::reservation.isInitialized) {
            val tickets = ticketMachine.tickets(List(purchaseCount.value) { TicketType.DEFAULT })
            reservation.initTickets(tickets)
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

    private fun initWithMovie() {
        val movie = movie()
        if (movie == null) {
            showMissingMovieDialog()
        } else {
            initMovieContent(movie)
            initReservation(movie)
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
            posterView.setImageResource(imageUrl)
            titleView.text = title
            screeningDateView.text = formatScreeningDate(screeningDate)
            runningTimeView.text = getString(R.string.formatted_minute, runningTime.time)
        }
    }

    private fun initReservation(movie: Movie) {
        initDateSpinner(movie.screeningDate)
        initTimeSpinner(movie.screeningDate.startDate)
        initReservationData(movie)
        updateTicketCount()
        initButtonListeners()
    }

    private fun initReservationData(movie: Movie) {
        this.reservation = Reservation(movie.title, getSelectedDateTime(), null)
    }

    private fun initDateSpinner(screeningDate: ScreeningDate) {
        val dates = reservationScheduler.reservableDates(screeningDate, LocalDate.now())

        dateSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dates)
        dateSpinner.onItemSelectedListener = createDateSelectionListener(dates)
    }

    private fun initTimeSpinner(startDate: LocalDate) {
        updateTimeSpinner(startDate)
        timeSpinner.onItemSelectedListener = createTimeSelectionListener()
    }

    private fun screeningTimes(selectedDate: LocalDate): List<LocalTime> =
        reservationScheduler.reservableTimes(
            selectedDate,
            LocalDateTime.now(),
        )

    private fun updateTimeSpinner(selectedDate: LocalDate) {
        screeningTimes(selectedDate)
        timeSpinner.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, screeningTimes(selectedDate))
    }

    private fun getSelectedDateTime(): LocalDateTime =
        LocalDateTime.of(
            dateSpinner.selectedItem as LocalDate,
            timeSpinner.selectedItem as LocalTime,
        )

    private fun updateTicketCount() {
        ticketCountTextView.text = purchaseCount.value.toString()
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
                val selectedDate = dates[position]
                updateTimeSpinner(selectedDate)
                timeSpinner.setSelection(savedTimePosition.coerceAtMost(screeningTimes(selectedDate).size - 1))
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
                savedTimePosition = position
                reservation = reservation.updateReservedTime(getSelectedDateTime())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

    companion object {
        private const val KEY_DATE_POSITION = "datePosition"
        private const val KEY_TIME_POSITION = "timePosition"
        private const val KEY_PURCHASE_COUNT = "purchase_count"
        private const val KEY_SELECTED_DATE = "selectedDate"
        const val KEY_RESERVE_ACTIVITY_MOVIE = "key_reserve_activity_movie"
    }
}
