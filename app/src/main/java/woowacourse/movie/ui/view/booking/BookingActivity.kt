package woowacourse.movie.ui.view.booking

import android.content.Context
import android.content.Intent
import android.os.Bundle
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
import woowacourse.movie.domain.model.booking.ScreeningDateSelectListener
import woowacourse.movie.domain.model.booking.ScreeningDateSpinner
import woowacourse.movie.domain.model.booking.result.BookingResult
import woowacourse.movie.domain.model.movie.Movie
import woowacourse.movie.presenter.booking.BookingContract
import woowacourse.movie.presenter.booking.BookingPresenter
import woowacourse.movie.ui.model.booking.BookingResultUiModel
import woowacourse.movie.ui.model.movie.MovieUiModel
import woowacourse.movie.ui.model.movie.setPosterImage
import woowacourse.movie.util.DateTimeUtil
import woowacourse.movie.util.DateTimeUtil.MOVIE_DATE_DELIMITER
import woowacourse.movie.util.DateTimeUtil.MOVIE_TIME_DELIMITER
import woowacourse.movie.util.DateTimeUtil.toLocalDate
import woowacourse.movie.util.DateTimeUtil.toLocalTime
import woowacourse.movie.util.DialogUtil
import woowacourse.movie.util.mapper.BookingResultModelMapper
import java.time.LocalDate
import java.time.LocalTime

class BookingActivity : AppCompatActivity(), BookingContract.View {
    private lateinit var presenter: BookingContract.Presenter
    private lateinit var bookingResult: BookingResult
    private lateinit var screeningDateSpinner: ScreeningDateSpinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking)
        applySystemBarInsets()

        presenter = BookingPresenter(view = this@BookingActivity, movieUiModel = movieOrNull())
        presenter.loadScreeningPeriods()

        bookingResult = BookingResult("tmp", 0, LocalDate.now(), LocalTime.now())
        setUpHeadCount()

        setUpReserveConfirm()
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
            EXTRA_SELECTED_MOVIE_ITEM,
            MovieUiModel::class.java,
        )
    }

    override fun showMovieInfo(movieUiModel: MovieUiModel) {
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

    override fun showErrorMessage(message: String) {
        DialogUtil.showError(
            this@BookingActivity,
            message = message,
        )
    }

    override fun setScreeningDateSpinner(dates: List<String>) {
        screeningDateSpinner =
            ScreeningDateSpinner(
                findViewById(R.id.spinner_screening_date),
                dates,
            )
        screeningDateSpinner.setOnItemSelectedListener(
            ScreeningDateSelectListener(
                onSelect = { screeningDate ->
                    presenter.updateScreeningDate(screeningDate)
                },
            ),
        )
    }

    override fun showScreeningDate(position: Int) {
        screeningDateSpinner.setSelect(position)
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
                        selectedSpinnerTime.toLocalTime(MOVIE_TIME_DELIMITER)
                    bookingResult = bookingResult.updateTime(selectedTime)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    val time: String = screeningTimeSpinner.getItemAtPosition(0).toString()
                    bookingResult =
                        bookingResult.updateTime(time.toLocalTime(MOVIE_TIME_DELIMITER))
                }
            }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setUpHeadCount() {
        val headCountView: TextView = findViewById(R.id.tv_people_count)
        val uiModel = BookingResultModelMapper.toUi(bookingResult)

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

    private fun showConfirmDialog2(bookingResultUiModel: BookingResultUiModel) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dig_title))
            .setMessage(getString(R.string.dig_message))
            .setPositiveButton(getString(R.string.dig_btn_positive_message)) { _, _ ->
                val intent = BookingCompleteActivity.newIntent(this, bookingResultUiModel)
                startActivity(intent)
            }
            .setNegativeButton(getString(R.string.dig_btn_negative_message)) { dialog, _ ->
                dialog.dismiss()
            }.setCancelable(false)
            .show()
    }

    private fun showConfirmDialog(bookingResultUiModel: BookingResultUiModel) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.dig_title))
            .setMessage(getString(R.string.dig_message))
            .setPositiveButton(getString(R.string.dig_btn_positive_message)) { _, _ ->
                val intent = BookingCompleteActivity.newIntent(this, bookingResultUiModel)
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
            putInt(SAVED_BOOKING_HEAD_COUNT, bookingResult.headCount)
            val bookingResultUiModel = BookingResultModelMapper.toUi(bookingResult)
            putString(SAVED_BOOKING_SCREENING_DATE, bookingResultUiModel.selectedDate)
            putString(SAVED_BOOKING_SCREENING_TIME, bookingResultUiModel.selectedTime)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val savedCount = savedInstanceState.getInt(SAVED_BOOKING_HEAD_COUNT)
        val savedScreeningDate =
            savedInstanceState.getString(SAVED_BOOKING_SCREENING_DATE)
        val savedScreeningTime =
            savedInstanceState.getString(SAVED_BOOKING_SCREENING_TIME)

        val date =
            savedScreeningDate?.toLocalDate(MOVIE_DATE_DELIMITER) ?: LocalDate.now()
        val time =
            savedScreeningTime?.toLocalTime(MOVIE_TIME_DELIMITER) ?: LocalTime.now()

        val headCountView: TextView = findViewById(R.id.tv_people_count)
        headCountView.text = savedCount.toString()
        bookingResult = BookingResult(bookingResult.title, savedCount, date, time)
    }

    companion object {
        private const val EXTRA_SELECTED_MOVIE_ITEM = "extra_selected_movie_item"
        private const val SAVED_BOOKING_HEAD_COUNT = "savedstate_booking_head_count"
        private const val SAVED_BOOKING_SCREENING_DATE = "savedstate_booking_screening_date"

        private const val SAVED_BOOKING_SCREENING_TIME = "savedstate_booking_screening_time"

        fun newIntent(
            context: Context,
            movieUiModel: MovieUiModel,
        ): Intent {
            return Intent(
                context,
                BookingActivity::class.java,
            ).apply { putExtra(EXTRA_SELECTED_MOVIE_ITEM, movieUiModel) }
        }
    }
}
