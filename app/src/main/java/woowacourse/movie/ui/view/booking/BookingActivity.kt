package woowacourse.movie.ui.view.booking

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.compat.IntentCompat
import woowacourse.movie.domain.model.booking.Booking
import woowacourse.movie.domain.model.booking.BookingResult
import woowacourse.movie.domain.model.movie.Movie
import woowacourse.movie.ui.model.booking.BookingResultUiModel
import woowacourse.movie.ui.model.movie.MovieUiModel
import woowacourse.movie.ui.model.movie.setPosterImage
import woowacourse.movie.util.DateTimeUtil
import woowacourse.movie.util.DateTimeUtil.MOVIE_DATE_DELIMITER
import woowacourse.movie.util.DateTimeUtil.MOVIE_SPINNER_DATE_DELIMITER
import woowacourse.movie.util.DateTimeUtil.MOVIE_TIME_DELIMITER
import woowacourse.movie.util.Keys
import woowacourse.movie.util.mapper.BookingResultModelMapper
import woowacourse.movie.util.mapper.MovieModelMapper
import java.time.LocalDate
import java.time.LocalTime

class BookingActivity : AppCompatActivity() {
    private lateinit var bookingResult: BookingResult

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking)
        applySystemBarInsets()

        val movieUiData: MovieUiModel = movieOrNull() ?: return
        setUpMovieInfo(movieUiData)

        val movieData = MovieModelMapper.toDomain(movieUiData)
        val booking = Booking(movieData)
        initBookingResult(movieData, booking)

        setUpScreeningDateSpinner(movieData, booking)
        setUpScreeningTimeSpinner(movieData, booking)
        setUpHeadCount()

        setUpReserveConfirm()
    }

    private fun initBookingResult(
        movieData: Movie,
        booking: Booking,
    ) {
        val selectedDate = booking.screeningPeriods()[0]
        bookingResult =
            BookingResult(
                title = movieData.title,
                headCount = 0,
                selectedDate = selectedDate,
                selectedTime = booking.screeningTimes(selectedDate)[0],
            )
    }

    private fun applySystemBarInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun movieOrNull(): MovieUiModel? {
        return IntentCompat.getParcelableExtra(
            intent,
            Keys.Extra.SELECTED_MOVIE_ITEM,
            MovieUiModel::class.java,
        )
    }

    private fun setUpMovieInfo(movieUiModel: MovieUiModel) {
        val poster: ImageView = findViewById(R.id.img_booking_poster)
        val bookingTitle: TextView = findViewById(R.id.tv_booking_title)
        val bookingScreenDate: TextView = findViewById(R.id.tv_booking_screening_date)
        val bookingRunningTime: TextView = findViewById(R.id.tv_booking_running_time)

        poster.setPosterImage(movieUiModel.poster)
        bookingTitle.text = movieUiModel.title
        val screeningStartDate = movieUiModel.screeningStartDate
        val screeningEndDate = movieUiModel.screeningEndDate
        bookingScreenDate.text =
            getString(R.string.screening_date_period, screeningStartDate, screeningEndDate)
        bookingRunningTime.text = getString(R.string.minute_text, movieUiModel.runningTime)
    }

    private fun setUpScreeningDateSpinner(
        movieData: Movie,
        booking: Booking,
    ) {
        val screeningDateSpinner = findViewById<Spinner>(R.id.spinner_screening_date)
        val screeningPeriods = booking.screeningPeriods()

        screeningDateSpinner.adapter =
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                DateTimeUtil.toSpinnerDates(screeningPeriods),
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
                    val selectedSpinnerDate: String =
                        screeningDateSpinner.getItemAtPosition(position).toString()
                    val selectedDate =
                        DateTimeUtil.toLocalDate(selectedSpinnerDate, MOVIE_SPINNER_DATE_DELIMITER)
                    bookingResult = bookingResult.updateDate(selectedDate)
                    setUpScreeningTimeSpinner(movieData, booking)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    val date: String = screeningDateSpinner.getItemAtPosition(0).toString()
                    bookingResult =
                        bookingResult.updateDate(
                            DateTimeUtil.toLocalDate(date, MOVIE_SPINNER_DATE_DELIMITER),
                        )
                }
            }
    }

    private fun setUpScreeningTimeSpinner(
        movieData: Movie,
        booking: Booking,
    ) {
        val screeningTimeSpinner = findViewById<Spinner>(R.id.spinner_screening_time)
        val screeningTimes = booking.screeningTimes(bookingResult.selectedDate)

        if (screeningTimes.isEmpty()) {
            val nextDate = bookingResult.selectedDate.plusDays(1)
            val nextDayScreeningTimes =
                if (movieData.isScreeningEnd(nextDate)) {
                    emptyList()
                } else {
                    bookingResult = bookingResult.updateDate(nextDate)
                    val nextScreeningTimes = booking.screeningTimes(bookingResult.selectedDate)
                    bookingResult = bookingResult.updateTime(nextScreeningTimes.first())
                    nextScreeningTimes
                }

            screeningTimeSpinner.adapter =
                ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_item,
                    DateTimeUtil.toSpinnerTimes(nextDayScreeningTimes),
                )
        } else {
            screeningTimeSpinner.adapter =
                ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_item,
                    DateTimeUtil.toSpinnerTimes(screeningTimes),
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
                    val selectedSpinnerTime: String =
                        screeningTimeSpinner.getItemAtPosition(position).toString()
                    val selectedTime =
                        DateTimeUtil.toLocalTime(selectedSpinnerTime, MOVIE_TIME_DELIMITER)
                    bookingResult = bookingResult.updateTime(selectedTime)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    val time: String = screeningTimeSpinner.getItemAtPosition(0).toString()
                    bookingResult =
                        bookingResult.updateTime(
                            DateTimeUtil.toLocalTime(
                                time,
                                MOVIE_TIME_DELIMITER,
                            ),
                        )
                }
            }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setUpHeadCount() {
        val headCountView: TextView = findViewById(R.id.tv_people_count)
        val uiModel = BookingResultModelMapper.toUi(bookingResult)
        Log.d("restore_inactivity", uiModel.headCount)
        headCountView.text = uiModel.headCount

        setUpPlusButton(headCountView)
        setUpMinusButton(headCountView)
    }

    private fun setUpPlusButton(headCountView: TextView) {
        val btnPlus = findViewById<Button>(R.id.btn_plus)
        btnPlus.setOnClickListener {
            bookingResult = bookingResult.plusHeadCount()
            val uiModel = BookingResultModelMapper.toUi(bookingResult)
            headCountView.text = uiModel.headCount
        }
    }

    private fun setUpMinusButton(headCountView: TextView) {
        val btnMinus = findViewById<Button>(R.id.btn_minus)
        btnMinus.setOnClickListener {
            if (bookingResult.isHeadCountValid()) bookingResult = bookingResult.minusHeadCount()
            val uiModel = BookingResultModelMapper.toUi(bookingResult)
            headCountView.text = uiModel.headCount
        }
    }

    private fun setUpReserveConfirm() {
        val btnReserveConfirm = findViewById<Button>(R.id.btn_reserve_confirm)
        btnReserveConfirm.setOnClickListener {
            if (bookingResult.isHeadCountValid()) {
                showConfirmDialog(BookingResultModelMapper.toUi(bookingResult))
            }
        }
    }

    private fun showConfirmDialog(bookingResultUiModel: BookingResultUiModel) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dig_title))
            .setMessage(getString(R.string.dig_message))
            .setPositiveButton(getString(R.string.dig_btn_positive_message)) { _, _ ->
                val intent = Intent(this, BookingCompleteActivity::class.java)
                intent.putExtra(Keys.Extra.BOOKING_RESULT, bookingResultUiModel)
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

        with(outState) {
            putInt(Keys.SavedState.BOOKING_HEAD_COUNT, bookingResult.headCount)
            val bookingResultUiModel = BookingResultModelMapper.toUi(bookingResult)
            putString(Keys.SavedState.BOOKING_SCREENING_DATE, bookingResultUiModel.selectedDate)
            putString(Keys.SavedState.BOOKING_SCREENING_TIME, bookingResultUiModel.selectedTime)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val savedCount = savedInstanceState.getInt(Keys.SavedState.BOOKING_HEAD_COUNT)
        val savedScreeningDate =
            savedInstanceState.getString(Keys.SavedState.BOOKING_SCREENING_DATE)
        val savedScreeningTime =
            savedInstanceState.getString(Keys.SavedState.BOOKING_SCREENING_TIME)

        val date =
            savedScreeningDate?.let { DateTimeUtil.toLocalDate(it, MOVIE_DATE_DELIMITER) }
                ?: LocalDate.now()
        val time =
            savedScreeningTime?.let { DateTimeUtil.toLocalTime(it, MOVIE_TIME_DELIMITER) }
                ?: LocalTime.now()

        val headCountView: TextView = findViewById(R.id.tv_people_count)
        headCountView.text = savedCount.toString()
        bookingResult = BookingResult(bookingResult.title, savedCount, date, time)
    }
}
