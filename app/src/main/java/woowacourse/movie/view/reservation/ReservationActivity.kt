package woowacourse.movie.view.reservation

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.ReservationCount
import woowacourse.movie.domain.model.ReservationInfo
import woowacourse.movie.view.base.BaseActivity
import woowacourse.movie.view.extension.getParcelableCompat
import woowacourse.movie.view.reservation.seat.SeatSelectionActivity
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ReservationActivity :
    BaseActivity(R.layout.activity_reservation),
    ReservationContract.View {
    private val presenter = ReservationPresenter(this)
    private var shouldIgnoreNextSelection = false

    private val tvReservationCount by lazy { findViewById<TextView>(R.id.tv_reservation_count) }
    private val spinnerDate by lazy { findViewById<Spinner>(R.id.spinner_reservation_date) }
    private val spinnerTime by lazy { findViewById<Spinner>(R.id.spinner_reservation_time) }
    private val dateSpinnerAdapter: ArrayAdapter<LocalDate> by lazy {
        ArrayAdapter(this, android.R.layout.simple_spinner_item, mutableListOf<LocalDate>()).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
    }
    private val timeSpinnerAdapter: ArrayAdapter<LocalTime> by lazy {
        ArrayAdapter(this, android.R.layout.simple_spinner_item, mutableListOf<LocalTime>()).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
    }

    private val unavailableDateTimeDialog by lazy {
        AlertDialog
            .Builder(this)
            .setMessage(R.string.unavailable_reservation_message)
            .setPositiveButton(R.string.confirm) { _, _ ->
                onBackPressedDispatcher.onBackPressed()
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val movie = intent?.getParcelableCompat<Movie>(BUNDLE_KEY_MOVIE)
        val count = savedInstanceState?.getInt(RESTORE_BUNDLE_KEY_RESERVATION_NUMBER)
        val reservationDateTime =
            savedInstanceState?.getString(RESTORE_BUNDLE_KEY_RESERVATION_DATETIME)
        presenter.loadData(movie, count, reservationDateTime)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val selectedDate = spinnerDate.selectedItem as? LocalDate
        val selectedTime = spinnerTime.selectedItem as? LocalTime

        val reservationDateTime =
            if (selectedDate != null && selectedTime != null) {
                LocalDateTime.of(
                    selectedDate,
                    selectedTime,
                )
            } else {
                ""
            }

        outState.apply {
            putString(RESTORE_BUNDLE_KEY_RESERVATION_DATETIME, reservationDateTime.toString())
            putInt(
                RESTORE_BUNDLE_KEY_RESERVATION_NUMBER,
                tvReservationCount.text.toString().toIntOrNull() ?: 1,
            )
        }
    }

    override fun showMovieDetail(movie: Movie) {
        setMovieInfo(movie)
        setupDateSpinner()
        setupListener()
    }

    override fun notifyInvalidReservationInfo() {
        showToast(getString(R.string.invalid_reservation_message))
    }

    override fun updateReservationCount(count: Int) {
        tvReservationCount.text = count.toString()
    }

    override fun updateDateSet(
        dates: List<LocalDate>,
        selectedDate: LocalDate?,
    ) {
        dateSpinnerAdapter.clear()
        dateSpinnerAdapter.addAll(dates)
        dateSpinnerAdapter.notifyDataSetChanged()

        selectedDate?.let {
            val position = dateSpinnerAdapter.getPosition(it)
            spinnerDate.setSelection(position)
        }
    }

    override fun updateTimeSet(
        times: List<LocalTime>,
        selectedTime: LocalTime?,
    ) {
        timeSpinnerAdapter.clear()
        timeSpinnerAdapter.addAll(times)
        timeSpinnerAdapter.notifyDataSetChanged()

        selectedTime?.let {
            val timePosition = timeSpinnerAdapter.getPosition(it)
            if (timePosition >= 0) {
                spinnerTime.setSelection(timePosition)
            }
        }
    }

    override fun notifyUnavailableDate() {
        unavailableDateTimeDialog.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun navigateToSeatSelectionScreen(reservationInfo: ReservationInfo) {
        val intent = SeatSelectionActivity.newIntent(this, reservationInfo)
        startActivity(intent)
        finish()
    }

    private fun setupListener() {
        val btnReservationFinish = findViewById<Button>(R.id.btn_reservation_finish)
        btnReservationFinish.setOnClickListener {
            submitReservation()
        }

        val btnMinus = findViewById<Button>(R.id.btn_reservation_count_minus)
        btnMinus.setOnClickListener {
            runCatching {
                presenter.decreaseCount(1)
            }.onFailure {
                showToast(
                    getString(
                        R.string.invalid_reservation_count_message,
                        ReservationCount.MINIMUM_RESERVATION_COUNT,
                    ),
                )
            }
        }

        val btnPlus = findViewById<Button>(R.id.btn_reservation_count_plus)
        btnPlus.setOnClickListener {
            presenter.increaseCount(1)
        }
    }

    private fun submitReservation() {
        presenter.onReserve(
            reservationDate = spinnerDate.selectedItem as? LocalDate,
            reservationTime = spinnerTime.selectedItem as? LocalTime,
        )
    }

    private fun setMovieInfo(movie: Movie) {
        val formatter =
            DateTimeFormatter.ofPattern(getString(R.string.movie_screening_period_format))
        findViewById<ImageView>(R.id.iv_reservation_poster).setImageResource(movie.poster.toInt())
        findViewById<TextView>(R.id.tv_reservation_title).text = movie.title
        findViewById<TextView>(R.id.tv_screening_period).text =
            getString(
                R.string.movie_date,
                movie.screeningPeriod.startDate.format(formatter),
                movie.screeningPeriod.endDate
                    .format(formatter),
            )
        findViewById<TextView>(R.id.tv_reservation_running_time).text =
            getString(
                R.string.running_time,
                movie.runningTime.minute.toString(),
            )
    }

    private fun setupDateSpinner() {
        spinnerDate.adapter = dateSpinnerAdapter
        spinnerTime.adapter = timeSpinnerAdapter

        spinnerDate.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    if (shouldIgnoreNextSelection) {
                        shouldIgnoreNextSelection = false
                        return
                    }

                    val selectedDate = parent.getItemAtPosition(position) as LocalDate
                    presenter.selectDate(selectedDate)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

        presenter.selectDate(LocalDate.now())
    }

    companion object {
        private const val BUNDLE_KEY_MOVIE = "movie"
        private const val RESTORE_BUNDLE_KEY_RESERVATION_DATETIME = "reservation_datetime"
        private const val RESTORE_BUNDLE_KEY_RESERVATION_NUMBER = "reservation_number"

        fun newIntent(
            context: Context,
            movie: Movie,
        ): Intent =
            Intent(context, ReservationActivity::class.java).putExtra(
                BUNDLE_KEY_MOVIE,
                movie,
            )
    }
}
