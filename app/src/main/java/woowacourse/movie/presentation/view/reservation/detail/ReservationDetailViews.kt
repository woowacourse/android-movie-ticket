package woowacourse.movie.presentation.view.reservation.detail

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.ReservationCount
import woowacourse.movie.presentation.extension.setImage
import woowacourse.movie.presentation.extension.toDateTimeFormatter
import woowacourse.movie.presentation.util.CustomAlertDialog
import java.time.LocalDate
import java.time.LocalTime

class ReservationDetailViews(
    private val activity: ReservationDetailActivity,
) {
    private val tvReservationCount: TextView = activity.findViewById(R.id.tv_reservation_count)
    private val spinnerDate: Spinner = activity.findViewById(R.id.spinner_reservation_date)
    private val spinnerTime: Spinner = activity.findViewById(R.id.spinner_reservation_time)
    private val tvTitle: TextView = activity.findViewById(R.id.tv_reservation_title)
    private val ivPoster: ImageView = activity.findViewById(R.id.iv_reservation_poster)
    private val tvScreeningPeriod: TextView = activity.findViewById(R.id.tv_screening_period)
    private val tvRunningTime: TextView = activity.findViewById(R.id.tv_reservation_running_time)
    private val btnReservationFinish: Button = activity.findViewById(R.id.btn_reservation_finish)
    private val btnCountMinus: Button = activity.findViewById(R.id.btn_reservation_count_minus)
    private val btnCountPlus: Button = activity.findViewById(R.id.btn_reservation_count_plus)
    private val dateAdapter = createSpinnerAdapter<LocalDate>()
    private val timeAdapter = createSpinnerAdapter<LocalTime>()

    val dialog: CustomAlertDialog by lazy { CustomAlertDialog(activity) }

    fun bindMovieInfo(movie: Movie) {
        tvTitle.text = movie.title
        tvScreeningPeriod.text = formatPeriod(movie)
        tvRunningTime.text =
            activity.getString(R.string.running_time, movie.runningTime.minute.toString())
        movie.poster.setImage(ivPoster)
    }

    fun setOnReservationCountChanged(
        onDecrease: () -> Unit,
        onIncrease: () -> Unit,
    ) {
        btnCountMinus.setOnClickListener { onDecrease() }
        btnCountPlus.setOnClickListener { onIncrease() }
    }

    fun setOnFinishClickListener(action: () -> Unit) {
        btnReservationFinish.setOnClickListener { action() }
    }

    fun selectedSpinnerDateAndTime(): Pair<LocalDate?, LocalTime?> =
        spinnerDate.selectedItem as? LocalDate to spinnerTime.selectedItem as? LocalTime

    fun reservationCount(): Int? = tvReservationCount.toString().toIntOrNull()

    fun updateReservationCount(newCount: Int) {
        tvReservationCount.text = newCount.toString()
        updateReservationCountMinusButton(newCount)
    }

    fun setSpinners(
        onDateSelected: (LocalDate) -> Unit,
        shouldIgnoreNext: () -> Boolean,
        clearIgnoreNext: () -> Unit,
    ) {
        spinnerDate.adapter = dateAdapter
        spinnerTime.adapter = timeAdapter

        spinnerDate.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long,
                ) {
                    if (shouldIgnoreNext()) {
                        clearIgnoreNext()
                        return
                    }
                    val selected = parent.getItemAtPosition(position) as LocalDate
                    onDateSelected(selected)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    fun updateDateSpinnerItems(
        items: List<LocalDate>,
        selected: LocalDate? = null,
    ) {
        updateSpinnerItems(spinnerDate, dateAdapter, items, selected)
    }

    fun updateTimeSpinnerItems(
        items: List<LocalTime>,
        selected: LocalTime? = null,
    ) {
        updateSpinnerItems(spinnerTime, timeAdapter, items, selected)
    }

    private fun updateReservationCountMinusButton(count: Int) {
        btnCountMinus.apply {
            isClickable = count > ReservationCount.RESERVATION_MIN_COUNT
            alpha = if (isClickable) 1f else 0.4f
        }
    }

    private fun formatPeriod(movie: Movie): String {
        val formatter =
            activity.getString(R.string.movie_screening_period_format).toDateTimeFormatter()
        val start = movie.screeningPeriod.startDate.format(formatter)
        val end = movie.screeningPeriod.endDate.format(formatter)
        return activity.getString(R.string.movie_date, start, end)
    }

    private fun <T> createSpinnerAdapter(): ArrayAdapter<T> =
        ArrayAdapter(activity, android.R.layout.simple_spinner_item, mutableListOf<T>()).apply {
            setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        }

    private fun <T> updateSpinnerItems(
        spinner: Spinner,
        adapter: ArrayAdapter<T>,
        items: List<T>,
        selectedItem: T?,
    ) {
        adapter.clear()
        adapter.addAll(items)
        adapter.notifyDataSetChanged()

        val position = selectedItem?.let { adapter.getPosition(it) } ?: 0
        spinner.setSelection(position)
    }
}
