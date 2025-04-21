package woowacourse.movie.view.reservation

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ArrayAdapter
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.ReservationCount
import woowacourse.movie.domain.model.ReservationInfo
import woowacourse.movie.view.base.BaseActivity
import woowacourse.movie.view.extension.toDateTimeFormatter
import woowacourse.movie.view.model.MovieUiModel
import woowacourse.movie.view.model.toModel
import woowacourse.movie.view.model.toUiModel
import woowacourse.movie.view.reservation.result.ReservationResultActivity
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class ReservationActivity : BaseActivity(R.layout.activity_reservation) {
    private lateinit var views: ReservationViews
    private var reservationCount = ReservationCount()
    private var shouldIgnoreNextSelection = false
    private var movie: Movie? = null

    private val dateSpinnerAdapter: ArrayAdapter<LocalDate> by lazy {
        ArrayAdapter<LocalDate>(this, android.R.layout.simple_spinner_item, mutableListOf()).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
    }

    private val timeSpinnerAdapter: ArrayAdapter<LocalTime> by lazy {
        ArrayAdapter<LocalTime>(this, android.R.layout.simple_spinner_item, mutableListOf()).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }
    }

    private val reservationDialog by lazy {
        AlertDialog
            .Builder(this)
            .setTitle(R.string.reservation_dialog_title)
            .setMessage(R.string.reservation_dialog_message)
            .setCancelable(false)
            .setPositiveButton(R.string.reservation_dialog_positive) { _, _ -> navigateToResult() }
            .setNegativeButton(R.string.reservation_dialog_negative) { dialog, _ -> dialog.dismiss() }
    }

    override fun setupViews(savedInstanceState: Bundle?) {
        views = ReservationViews(this)
        setupActionBar()
        setupData()
        movie?.let { views.bindMovieInfo(it) }
        setupUI()
    }

    private fun setupUI() {
        views.setOnReservationCountChanged(
            onCountDecreased = {
                updateReservationCount { reservationCount -= 1 }
            },
            onCountIncreased = {
                updateReservationCount { reservationCount += 1 }
            },
        )

        views.setOnFinishClickListener {
            if (reservationCount.value >= ReservationCount.RESERVATION_MIN_COUNT) {
                reservationDialog.show()
            }
        }

        views.tvReservationCount.text = reservationCount.value.toString()

        views.setDateSpinner(
            adapter = dateSpinnerAdapter,
            onDateSelected = { selectedDate ->
                movie?.let { updateTimeSpinner(it, selectedDate) }
            },
            shouldIgnoreNext = { shouldIgnoreNextSelection },
            clearIgnoreNext = { shouldIgnoreNextSelection = false },
        )

        movie?.let {
            views.setSpinnerItems(
                views.spinnerDate,
                dateSpinnerAdapter,
                it.screeningPeriod.getAvailableDates(LocalDate.now()),
            )
        }
    }

    private fun updateReservationCount(updateCount: () -> Unit) {
        runCatching {
            updateCount()
        }.onFailure {
            showToast(
                getString(
                    R.string.invalid_reservation_count_message,
                    ReservationCount.RESERVATION_MIN_COUNT,
                ),
            )
        }
        views.tvReservationCount.text = reservationCount.value.toString()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        val date = views.spinnerDate.selectedItem as? LocalDate
        val time = views.spinnerTime.selectedItem as? LocalTime
        val reservationDateTime =
            if (date != null && time != null) LocalDateTime.of(date, time).toString() else ""
        outState.putString(RESTORE_BUNDLE_KEY_RESERVATION_DATETIME, reservationDateTime)
        outState.putInt(RESTORE_BUNDLE_KEY_RESERVATION_NUMBER, reservationCount.value)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        updateReservationCount {
            reservationCount =
                ReservationCount(savedInstanceState.getInt(RESTORE_BUNDLE_KEY_RESERVATION_NUMBER))
        }

        movie?.let {
            val availableDates = it.screeningPeriod.getAvailableDates(LocalDate.now())
            views.setSpinnerItems(views.spinnerDate, dateSpinnerAdapter, availableDates)

            val formatter = SPINNER_DATETIME_FORMAT.toDateTimeFormatter()
            val dateTime =
                LocalDateTime.parse(
                    savedInstanceState.getString(RESTORE_BUNDLE_KEY_RESERVATION_DATETIME),
                    formatter,
                )

            shouldIgnoreNextSelection = true
            views.setSpinnerItems(
                views.spinnerDate,
                dateSpinnerAdapter,
                availableDates,
                dateTime.toLocalDate(),
            )
            updateTimeSpinner(it, dateTime.toLocalDate(), dateTime.toLocalTime())
        }
    }

    private fun setupActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupData() {
        movie = intent?.getParcelableExtra<MovieUiModel>(BUNDLE_KEY_MOVIE)?.toModel()
    }

    private fun updateTimeSpinner(
        movie: Movie,
        date: LocalDate,
        selectedTime: LocalTime? = null,
    ) {
        val times = movie.screeningPeriod.getAvailableTimesFor(LocalDateTime.now(), date)
        views.setSpinnerItems(views.spinnerTime, timeSpinnerAdapter, times, selectedTime)
    }

    private fun navigateToResult() {
        val date = views.spinnerDate.selectedItem as? LocalDate
        val time = views.spinnerTime.selectedItem as? LocalTime

        if (date == null || time == null) {
            showToast(getString(R.string.invalid_reservation_datetime_message))
            return
        }

        val reservationInfo =
            ReservationInfo(
                title = views.tvTitle.text.toString(),
                reservationDateTime = LocalDateTime.of(date, time),
                reservationCount = reservationCount,
            )

        val intent = ReservationResultActivity.newIntent(this, reservationInfo)
        startActivity(intent)
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressedDispatcher.onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
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
