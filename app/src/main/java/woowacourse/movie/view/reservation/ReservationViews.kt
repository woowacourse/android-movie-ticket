package woowacourse.movie.view.reservation

import android.app.Activity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.Spinner
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.view.extension.toDateTimeFormatter
import java.time.LocalDate

class ReservationViews(
    private val activity: Activity,
) {
    val ivPoster: ImageView = activity.findViewById(R.id.iv_reservation_poster)
    val tvTitle: TextView = activity.findViewById(R.id.tv_reservation_title)
    val tvScreeningPeriod: TextView = activity.findViewById(R.id.tv_screening_period)
    val tvRunningTime: TextView = activity.findViewById(R.id.tv_reservation_running_time)
    val tvReservationCount: TextView = activity.findViewById(R.id.tv_reservation_count)
    val spinnerDate: Spinner = activity.findViewById(R.id.spinner_reservation_date)
    val spinnerTime: Spinner = activity.findViewById(R.id.spinner_reservation_time)
    private val btnReservationFinish: Button = activity.findViewById(R.id.btn_reservation_finish)
    private val btnCountMinus: Button = activity.findViewById(R.id.btn_reservation_count_minus)
    private val btnCountPlus: Button = activity.findViewById(R.id.btn_reservation_count_plus)

    fun bindMovieInfo(movie: Movie) {
        movie.poster.toIntOrNull()?.let { ivPoster.setImageResource(it) }
        tvTitle.text = movie.title
        val formatter = MOVIE_SCREENING_PERIOD_FORMAT.toDateTimeFormatter()
        val start = movie.screeningPeriod.startDate.format(formatter)
        val end = movie.screeningPeriod.endDate.format(formatter)
        tvScreeningPeriod.text = activity.getString(R.string.movie_date, start, end)
        tvRunningTime.text = activity.getString(R.string.running_time, movie.runningTime.toString())
    }

    fun setOnReservationCountChanged(
        onCountDecreased: () -> Unit,
        onCountIncreased: () -> Unit,
    ) {
        btnCountMinus.setOnClickListener {
            onCountDecreased()
        }

        btnCountPlus.setOnClickListener {
            onCountIncreased()
        }
    }

    fun setOnFinishClickListener(action: () -> Unit) {
        btnReservationFinish.setOnClickListener { action() }
    }

    fun setDateSpinner(
        adapter: ArrayAdapter<LocalDate>,
        onDateSelected: (LocalDate) -> Unit,
        shouldIgnoreNext: () -> Boolean,
        clearIgnoreNext: () -> Unit,
    ) {
        spinnerDate.adapter = adapter
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
                    val selectedDate = parent.getItemAtPosition(position) as LocalDate
                    onDateSelected(selectedDate)
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
    }

    fun <T> setSpinnerItems(
        spinner: Spinner,
        adapter: ArrayAdapter<T>,
        items: List<T>,
        selectedItem: T? = null,
    ) {
        adapter.clear()
        adapter.addAll(items)
        adapter.notifyDataSetChanged()
        spinner.adapter = adapter

        selectedItem?.let {
            val position = adapter.getPosition(it)
            if (position >= 0) spinner.setSelection(position)
        }
    }

    companion object {
        private const val MOVIE_SCREENING_PERIOD_FORMAT = "yyyy.M.d"
    }
}
