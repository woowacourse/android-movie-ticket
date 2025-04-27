package woowacourse.movie.ui.view.booking

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.compat.IntentCompat
import woowacourse.movie.domain.model.booking.ScreeningDateSelectListener
import woowacourse.movie.domain.model.booking.ScreeningDateSpinner
import woowacourse.movie.domain.model.booking.ScreeningTimeSelectListener
import woowacourse.movie.domain.model.booking.ScreeningTimeSpinner
import woowacourse.movie.presenter.booking.BookingContract
import woowacourse.movie.presenter.booking.BookingPresenter
import woowacourse.movie.ui.model.booking.BookingResultUiModel
import woowacourse.movie.ui.model.movie.MovieUiModel
import woowacourse.movie.ui.model.movie.setPosterImage
import woowacourse.movie.util.DateTimeUtil.MOVIE_DATE_DELIMITER
import woowacourse.movie.util.DateTimeUtil.MOVIE_TIME_DELIMITER
import woowacourse.movie.util.DateTimeUtil.toLocalDate
import woowacourse.movie.util.DateTimeUtil.toLocalTime
import woowacourse.movie.util.DialogUtil
import java.time.LocalDate
import java.time.LocalTime

class BookingActivity : AppCompatActivity(), BookingContract.View {
    private lateinit var presenter: BookingContract.Presenter
    private lateinit var screeningDateSpinner: ScreeningDateSpinner
    private lateinit var screeningTimeSpinner: ScreeningTimeSpinner
    private val headCountView: TextView by lazy { findViewById(R.id.tv_people_count) }
    private val btnPlus: Button by lazy { findViewById(R.id.btn_plus) }
    private val btnMinus: Button by lazy { findViewById(R.id.btn_minus) }
    private val btnReserveConfirm: Button by lazy { findViewById(R.id.btn_reserve_confirm) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking)
        applySystemBarInsets()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        presenter = BookingPresenter(view = this@BookingActivity, movieUiModel = movieOrNull())
        presenter.loadScreeningDateTimes()
        presenter.loadHeadCount()
        setButtonClickListener()
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

    override fun setScreeningTimeSpinner(times: List<String>) {
        screeningTimeSpinner =
            ScreeningTimeSpinner(
                findViewById(R.id.spinner_screening_time),
            )
        setScreeningTimeAdapter(times)
        screeningTimeSpinner.setOnItemSelectedListener(
            ScreeningTimeSelectListener(
                onSelect = { screeningTime ->
                    presenter.updateScreeningTime(screeningTime)
                },
            ),
        )
    }

    override fun showScreeningTime(position: Int) {
        screeningTimeSpinner.setSelect(position)
    }

    override fun setScreeningTimeAdapter(times: List<String>) {
        screeningTimeSpinner.updateAdapter(times)
    }

    override fun showHeadCount(count: String) {
        headCountView.text = count
    }

    override fun setButtonClickListener() {
        btnPlus.setOnClickListener { presenter.increaseHeadCount() }
        btnMinus.setOnClickListener { presenter.decreaseHeadCount() }
        btnReserveConfirm.setOnClickListener { presenter.reserve() }
    }

    override fun showConfirmDialog(bookingResultUiModel: BookingResultUiModel) {
        DialogUtil.makeDialog(
            activity = this@BookingActivity,
            title = getString(R.string.dig_title),
            message = getString(R.string.dig_message),
            positiveButtonName = getString(R.string.dig_btn_positive_message),
            negativeButtonName = getString(R.string.dig_btn_negative_message),
            moveTo = {
                startActivity(BookingCompleteActivity.newIntent(this, bookingResultUiModel))
            },
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
            EXTRA_SELECTED_MOVIE_ITEM,
            MovieUiModel::class.java,
        )
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        with(outState) {
//            putInt(SAVED_BOOKING_HEAD_COUNT, bookingResult.headCount)
//            val bookingResultUiModel = BookingResultModelMapper.toUi(bookingResult)
//            putString(SAVED_BOOKING_SCREENING_DATE, bookingResultUiModel.selectedDate)
//            putString(SAVED_BOOKING_SCREENING_TIME, bookingResultUiModel.selectedTime)
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
//        bookingResult = BookingResult(bookingResult.title, savedCount, date, time)
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
