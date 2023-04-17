package woowacourse.movie.ui.moviebookingactivity

import android.R
import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Spinner
import woowacourse.movie.model.ScreeningPeriodState
import woowacourse.movie.model.mapper.toDomain
import java.time.LocalDate
import java.time.LocalTime

class TimeSpinnerAdapter(
    private val timeSpinner: Spinner,
    private val screeningPeriodState: ScreeningPeriodState,
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
        times.addAll(screeningPeriodState.toDomain().getScreeningTime(date))
        timeAdapter.notifyDataSetChanged()
        if (recoverPosition != NULL_POSITION) {
            timeSpinner.setSelection(recoverPosition)
            recoverPosition = NULL_POSITION
        }
    }

    companion object {
        private const val NULL_POSITION = -1
    }
}
