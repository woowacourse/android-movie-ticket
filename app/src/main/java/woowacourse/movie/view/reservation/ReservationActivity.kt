package woowacourse.movie.view.reservation

import android.content.Intent
import android.os.Bundle
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
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ReservationActivity : BaseActivity(R.layout.activity_reservation) {
    private var reservationNumber: Int = 0

    override fun setupViews() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setDisplayMovieInfo()
        setDateSpinner()
        setListener()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> startActivity(Intent(this, MoviesActivity::class.java))
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setListener() {
        val btnMinus = findViewById<Button>(R.id.btn_reservation_number_minus)
        val btnPlus = findViewById<Button>(R.id.btn_reservation_number_plus)
        val tvReservationNumber = findViewById<TextView>(R.id.tv_reservation_number)
        reservationNumber = tvReservationNumber?.text.toString().toIntOrNull() ?: 0

        btnMinus?.setOnClickListener {
            if (reservationNumber > 0) tvReservationNumber?.text = (--reservationNumber).toString()
        }
        btnPlus?.setOnClickListener {
            tvReservationNumber?.text = (++reservationNumber).toString()
        }

        val btnReservationFinish = findViewById<Button>(R.id.btn_reservation_finish)
        val spinnerDate = findViewById<Spinner>(R.id.spinner_reservation_date)
        val spinnerTime = findViewById<Spinner>(R.id.spinner_reservation_time)

        btnReservationFinish?.setOnClickListener {
            if (reservationNumber == 0) return@setOnClickListener

            val reservationDate =
                spinnerDate
                    ?.getItemAtPosition(spinnerDate.selectedItemPosition)
                    .toString()
                    .replace("-", ".")

            val reservationTime =
                spinnerTime?.getItemAtPosition(spinnerDate.selectedItemPosition).toString()

            val reservationInfo =
                ReservationInfo(
                    title = findViewById<TextView>(R.id.tv_reservation_title).text.toString(),
                    reservationDateTime = "$reservationDate $reservationTime",
                    reservationNumber = reservationNumber,
                )
            val bundle =
                Bundle().apply {
                    putParcelable(getString(R.string.bundle_key_reservation_info), reservationInfo)
                }
            startActivity(Intent(this, ReservationResultActivity::class.java).putExtras(bundle))
        }
    }

    private fun setDisplayMovieInfo() {
        intent?.getParcelableExtra<Movie>(getString(R.string.bundle_key_movie))?.let { movie ->
            val ivReservationPoster = findViewById<ImageView>(R.id.iv_reservation_poster)
            ivReservationPoster.setImageResource(movie.posterResId)

            val tvMovieTitle = findViewById<TextView>(R.id.tv_reservation_title)
            tvMovieTitle?.let { it.text = movie.title }

            val tvScreeningPeriod = findViewById<TextView>(R.id.tv_screening_period)
            tvScreeningPeriod?.let {
                it.text =
                    movie.screeningPeriod.run {
                        getString(
                            R.string.movie_date,
                            startDate.convertLocalDateFormat(),
                            endDate.convertLocalDateFormat(),
                        )
                    }
            }

            val tvRunningTime = findViewById<TextView>(R.id.tv_reservation_running_time)
            tvRunningTime?.let {
                it.text = getString(R.string.running_time, movie.runningTime.toString())
            }
        }
    }

    private fun setDateSpinner() {
        intent?.getParcelableExtra<Movie>(getString(R.string.bundle_key_movie))?.let { movie ->
            val nowDateTime = LocalDateTime.now()
            val endDate = movie.screeningPeriod.endDate

            val dateList = mutableListOf<String>()
            var date = nowDateTime.toLocalDate()
            while (!date.isAfter(endDate)) {
                dateList.add(date.toString())
                date = date.plusDays(1)
            }

            val dateSpinner = findViewById<Spinner>(R.id.spinner_reservation_date)
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dateList)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            dateSpinner.adapter = adapter

            dateSpinner.onItemSelectedListener =
                object : OnItemSelectedListener {
                    override fun onItemSelected(
                        p0: AdapterView<*>?,
                        p1: View?,
                        p2: Int,
                        p3: Long,
                    ) {
                        val selectedDate =
                            LocalDate.parse(dateSpinner.getItemAtPosition(p2).toString())
                        val targetDateTime =
                            if (nowDateTime.toLocalDate() ==
                                selectedDate
                            ) {
                                nowDateTime
                            } else {
                                LocalDateTime.of(
                                    selectedDate.year,
                                    selectedDate.month,
                                    selectedDate.dayOfMonth,
                                    0,
                                    0,
                                )
                            }
                        setTimeSpinner(targetDateTime)
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {}
                }
        }
    }

    private fun setTimeSpinner(targetDateTime: LocalDateTime) {
        val date = targetDateTime.toLocalDate()
        val currentTime = targetDateTime.toLocalTime()

        val isWeekend = date.dayOfWeek == DayOfWeek.SATURDAY || date.dayOfWeek == DayOfWeek.SUNDAY

        val startHour = if (isWeekend) 9 else 10

        val timeList = mutableListOf<String>()
        for (hour in startHour until 24 step 2) {
            val showTime = LocalTime.of(hour, 0)
            if (showTime.isAfter(currentTime)) {
                timeList.add(showTime.toString())
            }
        }

        val timeSpinner = findViewById<Spinner>(R.id.spinner_reservation_time)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, timeList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        timeSpinner.adapter = adapter
    }
}
