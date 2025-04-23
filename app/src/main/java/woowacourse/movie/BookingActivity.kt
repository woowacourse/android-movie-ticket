package woowacourse.movie

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.BookingCompleteActivity.Companion.KEY_BOOKING_RESULT
import woowacourse.movie.mapper.IntentCompat
import woowacourse.movie.mapper.toUiModel
import woowacourse.movie.model.Booking
import woowacourse.movie.model.BookingResult
import woowacourse.movie.model.Movie
import woowacourse.movie.ui.adapter.ScreeningDateSpinnerAdapter
import woowacourse.movie.ui.adapter.ScreeningTimeSpinnerAdapter
import woowacourse.movie.ui.listener.ScreeningDateSelectedListener
import woowacourse.movie.ui.listener.ScreeningTimeSelectedListener
import java.time.LocalDate
import java.time.LocalTime

class BookingActivity : AppCompatActivity() {
    private lateinit var bookingResult: BookingResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking)
        setUpUi()

        val movieData = requireMovieOrFinish() ?: return
        bookingResult = restoreData(movieData, savedInstanceState)

        setUpMovieInfo(movieData)

        val booking = Booking(movieData)
        setUpScreeningDateSpinner(booking)
        setupScreeningTimeSpinner(booking)
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
    ): BookingResult {
        val savedCount = savedInstanceState?.getInt(KEY_HEAD_COUNT)
        val savedScreeningDate = savedInstanceState?.getString(KEY_SCREENING_DATE)
        val savedScreeningTime = savedInstanceState?.getString(KEY_SCREENING_TIME)

        val date = savedScreeningDate?.let { LocalDate.parse(it) } ?: LocalDate.now()
        val time = savedScreeningTime?.let { LocalTime.parse(it) } ?: LocalTime.now()

        val headCount = savedCount ?: 0
        return BookingResult(movie.title, headCount, date, time)
    }

    private fun requireMovieOrFinish(): Movie? {
        val movieData = IntentCompat.getParcelableExtra(intent, KEY_MOVIE_DATA, Movie::class.java)
        if (movieData == null) {
            Log.e("BookingActivity", "movieData가 null입니다. 인텐트에 영화 데이터가 포함되지 않았습니다.")
            Toast.makeText(this, getString(R.string.booking_toast_message), Toast.LENGTH_SHORT)
                .show()
            finish()
        }
        return movieData
    }

    private fun setUpMovieInfo(movieData: Movie) {
        val moviePoster = findViewById<ImageView>(R.id.img_booking_poster)
        val bookingTitle = findViewById<TextView>(R.id.tv_booking_title)
        val bookingScreenDate = findViewById<TextView>(R.id.tv_booking_screening_date)
        val bookingRunningTime = findViewById<TextView>(R.id.tv_booking_running_time)

        val movieUiData = movieData.toUiModel(resources)

        bookingTitle.text = movieUiData.title
        moviePoster.setImageResource(movieUiData.imageSource)
        bookingScreenDate.text = movieUiData.screeningPeriod
        bookingRunningTime.text = movieUiData.runningTimeText
    }

    private fun setUpScreeningDateSpinner(booking: Booking) {
        val screeningDateSpinner = findViewById<Spinner>(R.id.spinner_screening_date)
        val screeningPeriods: List<LocalDate> = booking.screeningPeriods()

        screeningDateSpinner.adapter = ScreeningDateSpinnerAdapter(this, screeningPeriods)
        val position = screeningPeriods.indexOf(bookingResult.selectedDate)
        screeningDateSpinner.setSelection(position)

        screeningDateSpinner.onItemSelectedListener =
            ScreeningDateSelectedListener(
                bookingResult = bookingResult,
                onBookingResultChanged = { updated -> bookingResult = updated },
                refreshTimeSpinner = { setupScreeningTimeSpinner(booking) },
            )
    }

    private fun setupScreeningTimeSpinner(booking: Booking) {
        val screeningTimeSpinner = findViewById<Spinner>(R.id.spinner_screening_time)
        val screeningTimes = booking.screeningTimes(bookingResult.selectedDate)

        if (screeningTimes.isEmpty()) {
            val nextDate = bookingResult.selectedDate.plusDays(1)
            bookingResult = bookingResult.updateDate(nextDate)
            val nextTimes = booking.screeningTimes(bookingResult.selectedDate)

            screeningTimeSpinner.adapter = ScreeningTimeSpinnerAdapter(this, nextTimes)
            nextTimes.firstOrNull()?.let {
                bookingResult = bookingResult.updateTime(it)
            }
        } else {
            screeningTimeSpinner.adapter = ScreeningTimeSpinnerAdapter(this, screeningTimes)
        }

        val position = screeningTimes.indexOf(bookingResult.selectedTime)
        screeningTimeSpinner.setSelection(position)

        screeningTimeSpinner.onItemSelectedListener =
            ScreeningTimeSelectedListener(
                bookingResult = bookingResult,
                onBookingResultChanged = { updated -> bookingResult = updated },
            )

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
            bookingResult = bookingResult.plusHeadCount()
            headCountView.text = bookingResult.headCount.toString()
        }
    }

    private fun setUpMinusButton(headCountView: TextView) {
        val btnMinus = findViewById<Button>(R.id.btn_minus)
        btnMinus.setOnClickListener {
            if (bookingResult.isHeadCountValid()) bookingResult = bookingResult.minusHeadCount()
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

    private fun showConfirmDialog(bookingResult: BookingResult) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dig_title))
            .setMessage(getString(R.string.dig_message))
            .setPositiveButton(getString(R.string.dig_btn_positive_message)) { _, _ ->
                val intent = Intent(this, BookingCompleteActivity::class.java)
                intent.putExtra(KEY_BOOKING_RESULT, bookingResult)
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

        outState.putInt(KEY_HEAD_COUNT, bookingResult.headCount)
        outState.putString(KEY_SCREENING_DATE, bookingResult.selectedDate.toString())
        outState.putString(KEY_SCREENING_TIME, bookingResult.selectedTime.toString())
    }

    companion object {
        const val KEY_MOVIE_DATA = "movieData"
        private const val KEY_HEAD_COUNT = "HEAD_COUNT"
        private const val KEY_SCREENING_DATE = "SCREENING_DATE"
        private const val KEY_SCREENING_TIME = "SCREENING_TIME"
    }
}
