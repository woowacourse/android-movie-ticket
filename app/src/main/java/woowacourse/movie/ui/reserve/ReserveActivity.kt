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
import woowacourse.movie.domain.model.movie.Movie
import woowacourse.movie.domain.model.movie.ScreeningDate
import woowacourse.movie.domain.model.reservation.Reservation
import woowacourse.movie.ui.extensions.serializableData
import woowacourse.movie.ui.factory.CustomAlertDialog
import woowacourse.movie.ui.factory.DialogInfo
import woowacourse.movie.ui.seat.SeatActivity
import woowacourse.movie.ui.seat.SeatActivity.Companion.KEY_SEAT_ACTIVITY_PURCHASE_COUNT
import woowacourse.movie.ui.seat.SeatActivity.Companion.KEY_SEAT_ACTIVITY_RESERVATION
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ReserveActivity : AppCompatActivity(), ReserveContract.View {
    private lateinit var dateSpinner: Spinner
    private lateinit var timeSpinner: Spinner
    private lateinit var ticketCountTextView: TextView
    private lateinit var posterView: ImageView
    private lateinit var titleView: TextView
    private lateinit var screeningDateView: TextView
    private lateinit var runningTimeView: TextView
    private lateinit var minusButton: Button
    private lateinit var plusButton: Button
    private lateinit var reserveButton: Button
    private val customAlertDialog = CustomAlertDialog(this)
    private var savedTimePosition = 0
    private lateinit var presenter: ReserveContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reserve)
        initSystemUI()
        initViewId()
        presenter = ReservePresenter(this)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val movie = movie()
        if (movie != null) {
            presenter.initMovie(movie)
        } else {
            showMissingMovieDialog()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(KEY_DATE_POSITION, dateSpinner.selectedItemPosition)
        outState.putInt(KEY_TIME_POSITION, timeSpinner.selectedItemPosition)
        outState.putInt(KEY_PURCHASE_COUNT, ticketCountTextView.text.toString().toInt())
        outState.putSerializable(KEY_SELECTED_DATE, dateSpinner.selectedItem as LocalDate)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val datePosition = savedInstanceState.getInt(KEY_DATE_POSITION)
        val timePosition = savedInstanceState.getInt(KEY_TIME_POSITION)
        val selectedDate =
            savedInstanceState.serializableData(KEY_SELECTED_DATE, LocalDate::class.java)
        val purchaseCount = savedInstanceState.getInt(KEY_PURCHASE_COUNT)
        dateSpinner.setSelection(datePosition)
        if (selectedDate != null) {
            presenter.initTimes(
                selectedDate,
                LocalDateTime.now(),
            )
        }
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
            presenter.decreasePurchaseCount()
        }
    }

    private fun plusButtonInit() {
        plusButton.setOnClickListener {
            presenter.increasePurchaseCount()
        }
    }

    private fun reserveButtonInit() {
        reserveButton.setOnClickListener {
            moveToReservationResult()
        }
    }

    private fun moveToReservationResult() {
        presenter.reserve()
    }

    fun movie(): Movie? = intent.serializableData(KEY_RESERVE_ACTIVITY_MOVIE, Movie::class.java)

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
        initDateSpinner()
        initTimeSpinner(movie.screeningDate.startDate)
        presenter.updateReservation(getSelectedDateTime())
        initButtonListeners()
    }

    private fun initDateSpinner() {
        presenter.initDates(LocalDate.now())
    }

    private fun initTimeSpinner(startDate: LocalDate) {
        presenter.initTimes(startDate, LocalDateTime.now())
    }

    private fun getSelectedDateTime(): LocalDateTime {
        val selectedDate = dateSpinner.selectedItem as? LocalDate
        val selectedTime = timeSpinner.selectedItem as? LocalTime
        val dialogInfo =
            DialogInfo(
                getString(R.string.error_reservation_title),
                getString(R.string.error_reservation_message),
                getString(R.string.confirm),
                null,
                ::finish,
            )
        if (selectedDate == null || selectedTime == null) customAlertDialog.show(dialogInfo)
        return LocalDateTime.of(
            selectedDate,
            selectedTime,
        )
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
                presenter.updateSelectedDate(
                    dates[position],
                    LocalDateTime.now(),
                )
                presenter.updateReservation(getSelectedDateTime())
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
                presenter.updateSelectedTime(position)
                presenter.updateReservation(getSelectedDateTime())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

    override fun initScreen(movie: Movie) {
        initMovieContent(movie)
        initReservation(movie)
        presenter.updateTicketCount()
    }

    override fun fetchDates(dates: List<LocalDate>) {
        dateSpinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dates)
        dateSpinner.onItemSelectedListener = createDateSelectionListener(dates)
    }

    override fun fetchTimes(times: List<LocalTime>) {
        timeSpinner.adapter =
            ArrayAdapter(this, android.R.layout.simple_spinner_item, times)
        timeSpinner.onItemSelectedListener = createTimeSelectionListener()
    }

    override fun fetchPurchaseCount(purchaseCount: Int) {
        ticketCountTextView.text = purchaseCount.toString()
    }

    override fun reserve(
        reservation: Reservation,
        purchaseCount: Int,
    ) {
        startActivity(
            Intent(this, SeatActivity::class.java).apply {
                putExtra(KEY_SEAT_ACTIVITY_RESERVATION, reservation)
                putExtra(KEY_SEAT_ACTIVITY_PURCHASE_COUNT, purchaseCount)
            },
        )
    }

    override fun showToast() {
        Toast.makeText(this, R.string.validate_min_movie_ticket, Toast.LENGTH_SHORT).show()
    }

    override fun dateOnClick(
        date: LocalDate,
        screeningTimesSize: Int,
    ) {
        presenter.initTimes(date, LocalDateTime.now())
        timeSpinner.setSelection(savedTimePosition.coerceAtMost(screeningTimesSize - 1))
        presenter.updateReservation(getSelectedDateTime())
    }

    override fun timeOnClick(position: Int) {
        savedTimePosition = position
    }

    companion object {
        private const val KEY_DATE_POSITION = "date_position"
        private const val KEY_TIME_POSITION = "time_position"
        private const val KEY_PURCHASE_COUNT = "purchase_count"
        private const val KEY_SELECTED_DATE = "selected_date"
        const val KEY_RESERVE_ACTIVITY_MOVIE = "key_reserve_activity_movie"
    }
}
