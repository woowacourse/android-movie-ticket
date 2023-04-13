package woowacourse.movie.ui.moviebookingactivity

import android.R
import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Spinner
import woowacourse.movie.domain.datetime.ScreeningPeriod
import java.time.LocalDate
import java.time.LocalTime

class TimeSpinnerAdapter(
    private val timeSpinner: Spinner,
    private val screeningPeriod: ScreeningPeriod,
    private var recoverPosition: Int,
    context: Context
) {
    private val times = mutableListOf<LocalTime>()

    private val timeAdapter = ArrayAdapter(
        context,
        R.layout.simple_spinner_dropdown_item,
        times
    )

    fun initAdapter() {
        timeSpinner.adapter = timeAdapter
    }

    fun updateTimeTable(date: LocalDate) {
        times.clear()
        times.addAll(screeningPeriod.getScreeningTime(date))
        timeAdapter.notifyDataSetChanged()
        if (recoverPosition != -1) {
            timeSpinner.setSelection(recoverPosition)
            recoverPosition = -1
        }
    }
}
