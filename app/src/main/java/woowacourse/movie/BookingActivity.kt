package woowacourse.movie

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.model.Booking
import woowacourse.movie.model.BookingResult
import woowacourse.movie.model.Movie
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class BookingActivity : AppCompatActivity() {
    private lateinit var bookingResult: BookingResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking)
        setUpUi()

        val movieData = movieOrNull() ?: return
        restoreData(movieData, savedInstanceState)

        setUpMovieInfo(movieData)

        val booking = Booking(movieData)
        setUpScreeningDateSpinner(booking)
        setUpScreeningTimeSpinner(booking)
        setUpHeadCount()

        setUpReserveConfirm()
    }

    private fun setUpUi() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun restoreData(
        movie: Movie,
        savedInstanceState: Bundle?,
    ) {
        val savedCount = savedInstanceState?.getInt("HEAD_COUNT")
        val savedScreeningDate = savedInstanceState?.getString("SCREENING_DATE")
        val savedScreeningTime = savedInstanceState?.getString("SCREENING_TIME")

        val date = savedScreeningDate ?: formatDate(LocalDate.now(), '-')
        val time = savedScreeningTime ?: formatTime(LocalTime.now())
        val headCount = savedCount ?: 0
        bookingResult = BookingResult(movie.title, headCount, date, time)
    }

    private fun movieOrNull(): Movie? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("movieData", Movie::class.java)
        } else {
            intent.getParcelableExtra("movieData")
        }
    }

    private fun setUpMovieInfo(movieData: Movie) {
        val bookingTitle = findViewById<TextView>(R.id.tv_booking_title)
        val bookingScreenDate = findViewById<TextView>(R.id.tv_booking_screening_date)
        val bookingRunningTime = findViewById<TextView>(R.id.tv_booking_running_time)

        bookingTitle.text = movieData.title
        val screeningStartDate = formatDate(movieData.screeningStartDate, '.')
        val screeningEndDate = formatDate(movieData.screeningEndDate, '.')
        bookingScreenDate.text =
            getString(R.string.screening_date_period, screeningStartDate, screeningEndDate)
        bookingRunningTime.text = getString(R.string.minute_text, movieData.runningTime)
    }

    private fun setUpScreeningDateSpinner(booking: Booking) {
        val screeningDateSpinner = findViewById<Spinner>(R.id.spinner_screening_date)
        val screeningPeriods = booking.screeningPeriods()

        screeningDateSpinner.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                screeningPeriods,
            )

        val position = screeningPeriods.indexOf(bookingResult.selectedDate)
        screeningDateSpinner.setSelection(position)

        screeningDateSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selectedDate = screeningDateSpinner.getItemAtPosition(position).toString()
                    bookingResult.updateDate(selectedDate)
                    setUpScreeningTimeSpinner(booking)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    val date = screeningDateSpinner.getItemAtPosition(0).toString()
                    bookingResult.updateDate(date)
                }
            }
    }

    private fun setUpScreeningTimeSpinner(booking: Booking) {
        val screeningTimeSpinner = findViewById<Spinner>(R.id.spinner_screening_time)
        val screeningTimes = booking.screeningTimes(bookingResult.selectedDate)

        if (screeningTimes.isEmpty()) {
            val nextDate = LocalDate.parse(bookingResult.selectedDate).plusDays(1)
            bookingResult.updateDate(nextDate.toString())
            val nextTimes = booking.screeningTimes(bookingResult.selectedDate)

            screeningTimeSpinner.adapter =
                ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_item,
                    nextTimes,
                )
            bookingResult.updateTime(nextTimes.firstOrNull().orEmpty())
        } else {
            screeningTimeSpinner.adapter =
                ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_item,
                    screeningTimes,
                )
        }

        val position = screeningTimes.indexOf(bookingResult.selectedTime)
        screeningTimeSpinner.setSelection(position)

        screeningTimeSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    val selectedTime = screeningTimeSpinner.getItemAtPosition(position).toString()
                    bookingResult.updateTime(selectedTime)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    screeningTimeSpinner.getItemAtPosition(0).toString()
                }
            }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setUpHeadCount() {
        val headCountView: TextView = findViewById(R.id.tv_people_count)
        headCountView.text = bookingResult.headCount.toString()

        setUpPlusButton(headCountView)
        setUpMinusButton(headCountView)
    }

    private fun setUpPlusButton(headCountView: TextView) {
        val btnPlus = findViewById<Button>(R.id.btn_plus)
        btnPlus.setOnClickListener {
            bookingResult.plusHeadCount()
            headCountView.text = bookingResult.headCount.toString()
        }
    }

    private fun setUpMinusButton(headCountView: TextView) {
        val btnMinus = findViewById<Button>(R.id.btn_minus)
        btnMinus.setOnClickListener {
            if (bookingResult.isHeadCountValid()) bookingResult.minusHeadCount()
            headCountView.text = bookingResult.headCount.toString()
        }
    }

    private fun setUpReserveConfirm() {
        val btnReserveConfirm = findViewById<Button>(R.id.btn_reserve_confirm)
        btnReserveConfirm.setOnClickListener {
            if (bookingResult.isHeadCountValid()) {
                showConfirmDialog(bookingResult)
            }
        }
    }

    private fun formatDate(
        date: LocalDate,
        delimiter: Char,
    ): String {
        return date.format(DateTimeFormatter.ofPattern("yyyy${delimiter}M${delimiter}d"))
    }

    private fun formatTime(time: LocalTime): String {
        return time.format(DateTimeFormatter.ofPattern("kk:mm"))
    }

    private fun showConfirmDialog(bookingResult: BookingResult) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dig_title))
            .setMessage(getString(R.string.dig_message))
            .setPositiveButton(getString(R.string.dig_btn_positive_message)) { _, _ ->
                val intent = Intent(this, BookingCompleteActivity::class.java)
                intent.putExtra("bookingResult", bookingResult)
                startActivity(intent)
            }
            .setNegativeButton(getString(R.string.dig_btn_negative_message)) { dialog, _ ->
                dialog.dismiss()
            }.setCancelable(false)
            .show()
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        outState.putInt("HEAD_COUNT", bookingResult.headCount)
        outState.putString("SCREENING_DATE", bookingResult.selectedDate)
        outState.putString("SCREENING_TIME", bookingResult.selectedTime)
    }
}
