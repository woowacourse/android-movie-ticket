package woowacourse.movie.ui.view.booking

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import woowacourse.movie.R
import woowacourse.movie.compat.IntentCompat
import woowacourse.movie.presenter.booking.BookingContract
import woowacourse.movie.presenter.booking.BookingPresenter
import woowacourse.movie.ui.model.booking.BookingResultUiModel
import woowacourse.movie.ui.model.movie.MovieUiModel
import woowacourse.movie.ui.model.movie.setPosterImage
import woowacourse.movie.ui.view.booking.seat.BookingSeatActivity
import woowacourse.movie.util.DateTimeUtil.MOVIE_SPINNER_DATE_DELIMITER
import woowacourse.movie.util.DialogUtil

class BookingActivity : AppCompatActivity(), BookingContract.View {
    private lateinit var presenter: BookingPresenter
    private lateinit var screeningDateSpinner: ScreeningDateSpinner
    private lateinit var screeningTimeSpinner: ScreeningTimeSpinner
    private lateinit var viewHolder: BookingViewHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_booking)
        applySystemBarInsets()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewHolder = BookingViewHolder(findViewById(R.id.main))

        presenter = BookingPresenter(view = this@BookingActivity, movieUiModel = movieOrNull())
        presenter.loadScreeningDateTimes()
        setButtonClickListener()
    }

    override fun showMovieInfo(movieUiModel: MovieUiModel) {
        viewHolder.poster.setPosterImage(movieUiModel.poster)
        viewHolder.bookingTitle.text = movieUiModel.title
        val screeningStartDate = movieUiModel.screeningStartDate
        val screeningEndDate = movieUiModel.screeningEndDate
        viewHolder.bookingScreenDate.text =
            getString(R.string.screening_date_period, screeningStartDate, screeningEndDate)
        viewHolder.bookingRunningTime.text =
            getString(R.string.minute_text, movieUiModel.runningTime)
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
                viewHolder.spinnerScreeningDate,
                dates,
            )
        screeningDateSpinner.setOnItemSelectedListener(
            ScreeningDateSelectListener(
                onSelect = { screeningDate ->
                    presenter.updateScreeningDate(screeningDate, MOVIE_SPINNER_DATE_DELIMITER)
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
                viewHolder.spinnerScreeningTime,
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
        viewHolder.headCountView.text = count
    }

    override fun setButtonClickListener() {
        viewHolder.btnPlus.setOnClickListener { presenter.increaseHeadCount() }
        viewHolder.btnMinus.setOnClickListener { presenter.decreaseHeadCount() }
        viewHolder.btnReserveConfirm.setOnClickListener { presenter.reserve() }
    }

    override fun moveTo(bookingResultUiModel: BookingResultUiModel) {
        startActivity(BookingSeatActivity.newIntent(this, bookingResultUiModel))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        with(outState) {
            putInt(SAVED_BOOKING_HEAD_COUNT, presenter.bookingResultUiModel.headCount.toInt())
            putString(SAVED_BOOKING_SCREENING_DATE, presenter.bookingResultUiModel.selectedDate)
            putString(SAVED_BOOKING_SCREENING_TIME, presenter.bookingResultUiModel.selectedTime)
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        val savedCount = savedInstanceState.getInt(SAVED_BOOKING_HEAD_COUNT)
        val savedScreeningDate =
            savedInstanceState.getString(SAVED_BOOKING_SCREENING_DATE)
        val savedScreeningTime =
            savedInstanceState.getString(SAVED_BOOKING_SCREENING_TIME)

        presenter.restoreBookingResult(savedCount, savedScreeningDate, savedScreeningTime)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
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
