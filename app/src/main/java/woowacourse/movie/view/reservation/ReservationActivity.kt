package woowacourse.movie.view.reservation

import android.app.AlertDialog
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
import woowacourse.movie.domain.model.ReservationInfo
import woowacourse.movie.view.base.BaseActivity
import woowacourse.movie.view.extension.convertLocalDateFormat
import woowacourse.movie.view.movies.MoviesActivity
import woowacourse.movie.view.reservation.result.ReservationResultActivity
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ReservationActivity : BaseActivity(R.layout.activity_reservation) {
    private var reservationNumber = 0
    private var movie: Movie? = null

    private var shouldIgnoreNextSelection = false

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

    private val tvReservationNumber by lazy { findViewById<TextView>(R.id.tv_reservation_number) }
    private val spinnerDate by lazy { findViewById<Spinner>(R.id.spinner_reservation_date) }
    private val spinnerTime by lazy { findViewById<Spinner>(R.id.spinner_reservation_time) }
    private val btnReservationFinish by lazy { findViewById<Button>(R.id.btn_reservation_finish) }
    private val btnMinus by lazy { findViewById<Button>(R.id.btn_reservation_number_minus) }
    private val btnPlus by lazy { findViewById<Button>(R.id.btn_reservation_number_plus) }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        val selectedDate = spinnerDate.selectedItem as? LocalDate
        val selectedTime = spinnerTime.selectedItem as? LocalTime

        val reservationDateTime =
            if (selectedDate != null && selectedTime != null) {
                LocalDateTime
                    .of(selectedDate, selectedTime)
                    .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))
            } else {
                ""
            }

        val reservationInfo =
            ReservationInfo(
                title = findViewById<TextView>(R.id.tv_reservation_title).text.toString(),
                reservationDateTime = reservationDateTime,
                reservationNumber = reservationNumber,
            )

        outState.putParcelable(getString(R.string.bundle_key_reservation_info), reservationInfo)
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupData()
        setMovieInfo()
        setupListener()
        setupDateSpinner()

        savedInstanceState?.getParcelable<ReservationInfo>(getString(R.string.bundle_key_reservation_info))?.let { reservationInfo ->
            reservationNumber = reservationInfo.reservationNumber
            tvReservationNumber.text = reservationNumber.toString()

            val dateTime =
                LocalDateTime.parse(
                    reservationInfo.reservationDateTime,
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
                )

            val selectedDate = dateTime.toLocalDate()

            movie?.let {
                val availableDates = it.screeningPeriod.getAvailableDates(LocalDateTime.now())

                dateSpinnerAdapter.clear()
                dateSpinnerAdapter.addAll(availableDates)
                dateSpinnerAdapter.notifyDataSetChanged()

                val datePosition = dateSpinnerAdapter.getPosition(dateTime.toLocalDate())
                if (datePosition >= 0) {
                    shouldIgnoreNextSelection = true
                    spinnerDate.setSelection(datePosition)
                    setupTimeSpinner(it, selectedDate, dateTime.toLocalTime())
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            startActivity(Intent(this, MoviesActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupData() {
        reservationNumber = tvReservationNumber.text.toString().toIntOrNull() ?: 0
        movie = intent.getParcelableExtra(getString(R.string.bundle_key_movie))
    }

    private fun setupListener() {
        btnMinus.setOnClickListener {
            if (reservationNumber > 0) {
                tvReservationNumber.text = (--reservationNumber).toString()
            }
        }

        btnPlus.setOnClickListener {
            tvReservationNumber.text = (++reservationNumber).toString()
        }

        btnReservationFinish.setOnClickListener {
            if (reservationNumber == 0) return@setOnClickListener
            showReservationDialog()
        }
    }

    private fun showReservationDialog() {
        AlertDialog
            .Builder(this)
            .setTitle(getString(R.string.reservation_dialog_title))
            .setMessage(getString(R.string.reservation_dialog_message))
            .setCancelable(false)
            .setPositiveButton(getString(R.string.reservation_dialog_positive)) { _, _ -> navigateToResult() }
            .setNegativeButton(getString(R.string.reservation_dialog_negative)) { dialog, _ -> dialog.dismiss() }
            .show()
    }

    private fun navigateToResult() {
        val reservationDate = spinnerDate.selectedItem.toString().replace("-", ".")
        val reservationTime = spinnerTime.selectedItem.toString()

        val reservationInfo =
            ReservationInfo(
                title = findViewById<TextView>(R.id.tv_reservation_title).text.toString(),
                reservationDateTime = "$reservationDate $reservationTime",
                reservationNumber = reservationNumber,
            )

        val intent =
            Intent(this, ReservationResultActivity::class.java).apply {
                putExtra(getString(R.string.bundle_key_reservation_info), reservationInfo)
            }
        startActivity(intent)
    }

    private fun setMovieInfo() {
        movie?.let { movie ->
            findViewById<ImageView>(R.id.iv_reservation_poster).setImageResource(movie.posterResId)
            findViewById<TextView>(R.id.tv_reservation_title).text = movie.title
            findViewById<TextView>(R.id.tv_screening_period).text =
                getString(
                    R.string.movie_date,
                    movie.screeningPeriod.startDate.convertLocalDateFormat(),
                    movie.screeningPeriod.endDate.convertLocalDateFormat(),
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

        if (spinnerDate.onItemSelectedListener == null) {
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
        }

        movie?.let {
            val nowDateTime = LocalDateTime.now()
            val availableDates = it.screeningPeriod.getAvailableDates(nowDateTime)

            dateSpinnerAdapter.clear()
            dateSpinnerAdapter.addAll(availableDates)
            dateSpinnerAdapter.notifyDataSetChanged()
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
}
