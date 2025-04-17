package woowacourse.movie.view.reservation

import android.app.AlertDialog
import android.content.Intent
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
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

class ReservationActivity : BaseActivity(R.layout.activity_reservation) {
    private var reservationNumber = 0
    private var movie: Movie? = null

    private val tvReservationNumber by lazy { findViewById<TextView>(R.id.tv_reservation_number) }
    private val spinnerDate by lazy { findViewById<Spinner>(R.id.spinner_reservation_date) }
    private val spinnerTime by lazy { findViewById<Spinner>(R.id.spinner_reservation_time) }
    private val btnReservationFinish by lazy { findViewById<Button>(R.id.btn_reservation_finish) }
    private val btnMinus by lazy { findViewById<Button>(R.id.btn_reservation_number_minus) }
    private val btnPlus by lazy { findViewById<Button>(R.id.btn_reservation_number_plus) }

    override fun setupViews() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setupData()
        setMovieInfo()
        setupDateSpinner()
        setupListener()
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

    private fun showReservationDialog() =
        AlertDialog
            .Builder(this)
            .setTitle(getString(R.string.reservation_dialog_title))
            .setMessage(getString(R.string.reservation_dialog_message))
            .setCancelable(false)
            .setPositiveButton(getString(R.string.reservation_dialog_positive)) { _, _ -> navigateToResult() }
            .setNegativeButton(getString(R.string.reservation_dialog_negative)) { dialog, _ -> dialog.dismiss() }
            .show()

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
        movie?.let { movie ->
            val nowDateTime = LocalDateTime.now()
            val availableDates = movie.screeningPeriod.getAvailableDates(nowDateTime)

            val dateAdapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, availableDates).apply {
                    setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                }
            spinnerDate.adapter = dateAdapter

            spinnerDate.onItemSelectedListener =
                object : OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View?,
                        position: Int,
                        id: Long,
                    ) {
                        val selectedDate =
                            LocalDate.parse(parent.getItemAtPosition(position).toString())
                        val targetDateTime =
                            if (nowDateTime.toLocalDate() == selectedDate) {
                                nowDateTime
                            } else {
                                LocalDateTime.of(selectedDate, LocalDateTime.MIN.toLocalTime())
                            }

                        setupTimeSpinner(movie, targetDateTime.toLocalDate())
                    }

                    override fun onNothingSelected(parent: AdapterView<*>?) {}
                }
        }
    }

    private fun setupTimeSpinner(
        movie: Movie,
        date: LocalDate,
    ) {
        val timeAdapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                movie.screeningPeriod.getAvailableTimesFor(date),
            ).apply {
                setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            }
        spinnerTime.adapter = timeAdapter
    }
}
