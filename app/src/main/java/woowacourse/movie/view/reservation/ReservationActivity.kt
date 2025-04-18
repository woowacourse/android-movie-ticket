package woowacourse.movie.view.reservation

import android.app.AlertDialog
import android.content.Intent
import android.os.Build
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
import woowacourse.movie.domain.model.ReservationInfo
import woowacourse.movie.view.base.BaseActivity
import woowacourse.movie.view.extension.toDateTimeFormatter
import woowacourse.movie.view.movies.MoviesActivity
import woowacourse.movie.view.reservation.result.ReservationResultActivity
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ReservationActivity : BaseActivity(R.layout.activity_reservation) {
    private var reservationCount = ReservationInfo.RESERVATION_MIN_NUMBER
    private var shouldIgnoreNextSelection = false
    private var movie: Movie? = null

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

    private val reservationDialog by lazy {
        AlertDialog
            .Builder(this)
            .setTitle(R.string.reservation_dialog_title)
            .setMessage(R.string.reservation_dialog_message)
            .setCancelable(false)
            .setPositiveButton(R.string.reservation_dialog_positive) { _, _ -> navigateToResult() }
            .setNegativeButton(R.string.reservation_dialog_negative) { dialog, _ -> dialog.dismiss() }
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupData()
        setMovieInfo()
        setupListener()
        setupDateSpinner()
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        restoreReservationInfo(savedInstanceState)
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
            putInt(RESTORE_BUNDLE_KEY_RESERVATION_NUMBER, reservationCount)
        }
    }

    private fun restoreReservationInfo(savedInstanceState: Bundle) {
        reservationCount = savedInstanceState.getInt(RESTORE_BUNDLE_KEY_RESERVATION_NUMBER)
        tvReservationCount.text = reservationCount.toString()

        movie?.let {
            val availableDates = it.screeningPeriod.getAvailableDates(LocalDateTime.now())
            updateDateSpinner(availableDates)

            val reservationDateTime =
                savedInstanceState.getString(RESTORE_BUNDLE_KEY_RESERVATION_DATETIME)
            val dateTime =
                SPINNER_DATETIME_FORMAT.toDateTimeFormatter()?.let { formatter ->
                    LocalDateTime.parse(reservationDateTime, formatter)
                }
            dateTime?.toLocalDate()?.let { selectedDate ->
                val datePosition = dateSpinnerAdapter.getPosition(selectedDate)
                if (datePosition >= 0) {
                    shouldIgnoreNextSelection = true
                    spinnerDate.setSelection(datePosition)
                    setupTimeSpinner(it, selectedDate, dateTime.toLocalTime())
                }
            }
        }
    }

    private fun updateDateSpinner(availableDates: List<LocalDate>) {
        dateSpinnerAdapter.clear()
        dateSpinnerAdapter.addAll(availableDates)
        dateSpinnerAdapter.notifyDataSetChanged()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            startActivity(Intent(this, MoviesActivity::class.java))
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupData() {
        reservationCount = tvReservationCount.text.toString().toIntOrNull()
            ?: ReservationInfo.RESERVATION_MIN_NUMBER
        tvReservationCount.text = reservationCount.toString()
        movie =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                intent?.getSerializableExtra(BUNDLE_KEY_MOVIE, Movie::class.java)
            } else {
                intent?.getSerializableExtra(BUNDLE_KEY_MOVIE) as? Movie
            }
    }

    private fun setupListener() {
        val btnReservationFinish = findViewById<Button>(R.id.btn_reservation_finish)
        btnReservationFinish.setOnClickListener {
            if (reservationCount < ReservationInfo.RESERVATION_MIN_NUMBER) return@setOnClickListener
            reservationDialog.show()
        }

        val btnMinus = findViewById<Button>(R.id.btn_reservation_count_minus)
        btnMinus.setOnClickListener {
            if (reservationCount > ReservationInfo.RESERVATION_MIN_NUMBER) {
                tvReservationCount.text = (--reservationCount).toString()
            }
        }

        val btnPlus = findViewById<Button>(R.id.btn_reservation_count_plus)
        btnPlus.setOnClickListener {
            tvReservationCount.text = (++reservationCount).toString()
        }
    }

    private fun navigateToResult() {
        val reservationDate = spinnerDate.selectedItem as? LocalDate
        val reservationTime = spinnerTime.selectedItem as? LocalTime

        if (reservationDate == null || reservationTime == null) {
            showToast(INVALID_RESERVATION_MESSAGE)
            return
        }

        val reservationInfo =
            ReservationInfo(
                title = findViewById<TextView>(R.id.tv_reservation_title).text.toString(),
                reservationDateTime = LocalDateTime.of(reservationDate, reservationTime),
                reservationCount = reservationCount,
            )

        val intent =
            Intent(this, ReservationResultActivity::class.java).apply {
                putExtra(ReservationResultActivity.BUNDLE_KEY_RESERVATION_INFO, reservationInfo)
            }
        startActivity(intent)
        finish()
    }

    private fun setMovieInfo() {
        movie?.let { movie ->
            movie.poster.toIntOrNull()?.let { findViewById<ImageView>(R.id.iv_reservation_poster).setImageResource(it) }
            findViewById<TextView>(R.id.tv_reservation_title).text = movie.title
            findViewById<TextView>(R.id.tv_screening_period).text =
                getString(
                    R.string.movie_date,
                    MOVIE_SCREENING_PERIOD_FORMAT.toDateTimeFormatter()?.let { formatter ->
                        movie.screeningPeriod.startDate.format(formatter)
                    },
                    MOVIE_SCREENING_PERIOD_FORMAT.toDateTimeFormatter()?.let { formatter ->
                        movie.screeningPeriod.endDate.format(formatter)
                    },
                )
            findViewById<TextView>(R.id.tv_reservation_running_time).text =
                getString(
                    R.string.running_time,
                    movie.runningTime.toString(),
                )
        }
    }

    private fun setupDateSpinner() {
        spinnerDate.adapter = dateSpinnerAdapter
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
                    movie?.let { setupTimeSpinner(it, selectedDate) }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

        movie?.let {
            val nowDateTime = LocalDateTime.now()
            val availableDates = it.screeningPeriod.getAvailableDates(nowDateTime)
            updateDateSpinner(availableDates)
        }
    }

    private fun setupTimeSpinner(
        movie: Movie,
        date: LocalDate,
        selectedTime: LocalTime? = null,
    ) {
        timeSpinnerAdapter.clear()
        val times = movie.screeningPeriod.getAvailableTimesFor(date)
        timeSpinnerAdapter.addAll(times)
        timeSpinnerAdapter.notifyDataSetChanged()
        spinnerTime.adapter = timeSpinnerAdapter

        selectedTime?.let {
            val timePosition = timeSpinnerAdapter.getPosition(it)
            if (timePosition >= 0) {
                spinnerTime.setSelection(timePosition)
            }
        }
    }

    companion object {
        const val BUNDLE_KEY_MOVIE = "movie"
        private const val RESTORE_BUNDLE_KEY_RESERVATION_DATETIME = "reservation_datetime"
        private const val RESTORE_BUNDLE_KEY_RESERVATION_NUMBER = "reservation_number"
        private const val SPINNER_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm"
        private const val MOVIE_SCREENING_PERIOD_FORMAT = "yyyy.M.d"
        private const val INVALID_RESERVATION_MESSAGE = "예약 날짜 및 시간을 선택해 주세요"
    }
}
