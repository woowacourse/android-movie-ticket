package woowacourse.movie.presentation.view.reservation.reservation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.ReservationInfo
import woowacourse.movie.presentation.base.BaseActivity
import woowacourse.movie.presentation.extension.getParcelableCompat
import woowacourse.movie.presentation.extension.toDateTimeFormatter
import woowacourse.movie.presentation.model.MovieUiModel
import woowacourse.movie.presentation.model.toModel
import woowacourse.movie.presentation.model.toUiModel
import woowacourse.movie.presentation.util.DialogInfo
import woowacourse.movie.presentation.view.reservation.result.ReservationResultActivity
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ReservationActivity :
    BaseActivity(R.layout.activity_reservation),
    ReservationContract.View {
    private val presenter: ReservationPresenter by lazy { ReservationPresenter(this) }
    private val views: ReservationViews by lazy { ReservationViews(this) }

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

    private val reservationConfirmationDialogInfo: DialogInfo by lazy {
        DialogInfo(
            title = getString(R.string.reservation_dialog_title),
            message = getString(R.string.reservation_dialog_message),
            positiveButtonText = getString(R.string.reservation_dialog_positive),
            negativeButtonText = getString(R.string.reservation_dialog_negative),
            onClickPositiveButton = { submitReservation() },
            onClickNegativeButton = { it.dismiss() },
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActionBar()

        shouldIgnoreNextSelection = savedInstanceState != null
        val (count, dateTime) = restoreReservationData(savedInstanceState)

        presenter.fetchData(count, dateTime) {
            intent?.getParcelableCompat<MovieUiModel>(BUNDLE_KEY_MOVIE)?.toModel()
        }
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

    override fun updateReservationCount(count: Int) {
        views.updateReservationCount(count)
    }

    override fun setScreen(movie: Movie) {
        views.bindMovieInfo(movie)
        setupDateSpinner()
        setupReservationCountControls()
        setupFinishButton()
    }

    override fun navigateToResult(reservationInfo: ReservationInfo) {
        val intent = ReservationResultActivity.newIntent(this, reservationInfo)
        startActivity(intent)
    }

    override fun showNoAvailableTimesDialog() {
        views.dialog.show(noAvailableTimesDialogInfo)
    }

    override fun showReservationConfirmationDialog() {
        views.dialog.show(reservationConfirmationDialogInfo)
    }

    override fun updateDateSpinner(
        dates: List<LocalDate>,
        times: List<LocalTime>,
        selectedDateTime: LocalDateTime?,
    ) {
        val selectedDate = selectedDateTime?.toLocalDate()
        val selectedTime = selectedDateTime?.toLocalTime()
        views.updateDateSpinnerItems(dates, selectedDate)
        updateTimeSpinner(times, selectedTime)
    }

    override fun updateTimeSpinner(
        times: List<LocalTime>,
        selectedTime: LocalTime?,
    ) {
        views.updateTimeSpinnerItems(times, selectedTime)
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
            views.dialog.show(reservationConfirmationDialogInfo)
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

    private fun submitReservation() {
        val (date, time) = views.selectedSpinnerDateAndTime()
        if (date == null || time == null) {
            showToast(getString(R.string.invalid_reservation_datetime_message))
            return
        }

        presenter.onReserve(LocalDateTime.of(date, time))
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
            movie: Movie,
        ): Intent =
            Intent(context, ReservationActivity::class.java).putExtra(
                BUNDLE_KEY_MOVIE,
                movie.toUiModel(),
            )
    }
}
