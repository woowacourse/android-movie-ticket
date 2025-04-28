package woowacourse.movie.presentation.view.reservation.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import woowacourse.movie.R
import woowacourse.movie.presentation.base.BaseActivity
import woowacourse.movie.presentation.extension.getParcelableCompat
import woowacourse.movie.presentation.extension.toDateTimeFormatter
import woowacourse.movie.presentation.model.MovieUiModel
import woowacourse.movie.presentation.model.ReservationInfoUiModel
import woowacourse.movie.presentation.model.ScreenUiModel
import woowacourse.movie.presentation.util.DialogInfo
import woowacourse.movie.presentation.view.reservation.seat.ReservationSeatActivity
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ReservationDetailActivity :
    BaseActivity(R.layout.activity_reservation),
    ReservationDetailContract.View {
    private val presenter: ReservationDetailPresenter by lazy { ReservationDetailPresenter(this) }
    private val views: ReservationDetailViews by lazy { ReservationDetailViews(this) }

    private var shouldIgnoreNextSelection = false

    private val noAvailableTimesDialogInfo: DialogInfo by lazy {
        DialogInfo(
            title = getString(R.string.no_available_times_dialog_title),
            message = getString(R.string.no_available_times_dialog_message),
            positiveButtonText = getString(R.string.no_available_times_dialog_positive),
            onClickPositiveButton = {
                onBackPressedDispatcher.onBackPressed()
            },
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActionBar()

        shouldIgnoreNextSelection = savedInstanceState != null

        val movie = intent?.getParcelableCompat<MovieUiModel>(BUNDLE_KEY_MOVIE)
        val (count, dateTime) = restoreReservationData(savedInstanceState)
        presenter.fetchData(movie, count, dateTime)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        saveSpinnersData(outState)
        saveReservationCount(outState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun updateReservationCount(
        count: Int,
        isEnabled: Boolean,
    ) {
        views.updateReservationCount(count, isEnabled)
    }

    override fun showScreen(movie: MovieUiModel) {
        views.bindMovieInfo(movie)
        setupDateSpinner()
        setupReservationCountControls()
        setupFinishButton()
    }

    override fun notifyNoAvailableDates() {
        views.dialog.show(noAvailableTimesDialogInfo)
    }

    override fun notifyReservationConfirm(
        reservationInfo: ReservationInfoUiModel,
        screen: ScreenUiModel,
    ) {
        val intent = ReservationSeatActivity.newIntent(this, reservationInfo, screen)
        startActivity(intent)
    }

    override fun updateDates(
        dates: List<LocalDate>,
        times: List<LocalTime>,
        selectedDateTime: LocalDateTime?,
    ) {
        val selectedDate = selectedDateTime?.toLocalDate()
        val selectedTime = selectedDateTime?.toLocalTime()
        views.updateDateSpinnerItems(dates, selectedDate)
        updateTimes(times, selectedTime)
    }

    override fun updateTimes(
        times: List<LocalTime>,
        selectedTime: LocalTime?,
    ) {
        views.updateTimeSpinnerItems(times, selectedTime)
    }

    override fun notifyReservationLimitReached() {
        showToast(getString(R.string.reservation_count_limit_reached_message))
    }

    private fun setupActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupReservationCountControls() {
        views.setOnReservationCountChanged(
            onDecrease = { presenter.updateReservationCount(-1) },
            onIncrease = { presenter.updateReservationCount(1) },
        )
    }

    private fun setupFinishButton() {
        views.setOnFinishClickListener {
            val (date, time) = views.selectedSpinnerDateAndTime()
            if (date == null || time == null) {
                showToast(getString(R.string.invalid_reservation_datetime_message))
                return@setOnFinishClickListener
            }

            presenter.onReserve(LocalDateTime.of(date, time))
        }
    }

    private fun setupDateSpinner() {
        views.setSpinners(
            onDateSelected = { selectedDate -> presenter.onSelectDate(selectedDate) },
            shouldIgnoreNext = { shouldIgnoreNextSelection },
            clearIgnoreNext = {
                shouldIgnoreNextSelection = false
            },
        )
    }

    private fun saveSpinnersData(outState: Bundle) {
        val (date, time) = views.selectedSpinnerDateAndTime()
        val reservationDateTime = if (date != null && time != null) LocalDateTime.of(date, time) else null

        reservationDateTime?.let { dateTime ->
            outState.putString(RESTORE_BUNDLE_KEY_RESERVATION_DATETIME, dateTime.toString())
        }
    }

    private fun saveReservationCount(outState: Bundle) {
        views.reservationCount()?.let { count ->
            outState.putInt(RESTORE_BUNDLE_KEY_RESERVATION_NUMBER, count)
        }
    }

    private fun restoreReservationData(savedInstanceState: Bundle?): Pair<Int?, LocalDateTime?> {
        if (savedInstanceState == null) return null to null
        val count = savedInstanceState.getInt(RESTORE_BUNDLE_KEY_RESERVATION_NUMBER)

        val restoredDateTime = savedInstanceState.getString(RESTORE_BUNDLE_KEY_RESERVATION_DATETIME)
        val formatter = SPINNER_DATETIME_FORMAT.toDateTimeFormatter()
        val dateTime = restoredDateTime?.let { LocalDateTime.parse(it, formatter) }

        return count to dateTime
    }

    companion object {
        private const val BUNDLE_KEY_MOVIE = "movie"
        private const val RESTORE_BUNDLE_KEY_RESERVATION_DATETIME = "reservation_datetime"
        private const val RESTORE_BUNDLE_KEY_RESERVATION_NUMBER = "reservation_number"
        private const val SPINNER_DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm"

        fun newIntent(
            context: Context,
            movie: MovieUiModel,
        ): Intent = Intent(context, ReservationDetailActivity::class.java).putExtra(BUNDLE_KEY_MOVIE, movie)
    }
}
