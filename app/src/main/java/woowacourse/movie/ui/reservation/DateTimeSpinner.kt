package woowacourse.movie.ui.reservation

import android.view.View
import android.widget.Spinner
import woowacourse.movie.R
import woowacourse.movie.model.MovieState
import woowacourse.movie.util.setClickListener
import woowacourse.movie.util.setDefaultAdapter
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class DateTimeSpinner(
    view: View,
    movieState: MovieState,
    private val getDates: (MovieState) -> List<LocalDate>,
    private val getTimes: (LocalDate) -> List<LocalTime>
) {
    private val dateSpinner: Spinner = view.findViewById(R.id.date_spinner)
    private val timeSpinner: Spinner = view.findViewById(R.id.time_spinner)

    private val runningDates: List<LocalDate> = getDates(movieState)
    private var selectDate: LocalDate = runningDates[0]
        set(value) {
            field = value
            runningTimes = getTimes(field)
            setTimeSpinnerAdapter()
        }

    private var runningTimes: List<LocalTime> = getTimes(selectDate)
        set(value) {
            field = value
            selectTime = field[0]
        }
    private var selectTime: LocalTime = runningTimes[0]

    init {
        setDateSpinnerAdapter()
        setTimeSpinnerAdapter()
        dateSpinner.setClickListener({ position ->
            selectDate = runningDates[position]
        })
        timeSpinner.setClickListener({ position ->
            selectTime = runningTimes[position]
        })
    }

    fun getSelectDateTime(): LocalDateTime = LocalDateTime.of(selectDate, selectTime)

    fun updateSelectDateTime(selectLocalDate: LocalDate, selectLocalTime: LocalTime) {
        selectDate = selectLocalDate
        selectTime = selectLocalTime
        dateSpinner.setSelection(runningDates.indexOf(selectDate), false)
        timeSpinner.setSelection(runningTimes.indexOf(selectTime), false)
    }

    private fun setDateSpinnerAdapter() {
        dateSpinner.setDefaultAdapter(runningDates.map { it.toString() })
    }

    private fun setTimeSpinnerAdapter() {
        timeSpinner.setDefaultAdapter(runningTimes.map { it.toString() })
    }
}
