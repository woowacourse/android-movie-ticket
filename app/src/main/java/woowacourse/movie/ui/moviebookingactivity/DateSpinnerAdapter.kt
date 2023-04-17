package woowacourse.movie.ui.moviebookingactivity

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Spinner
import woowacourse.movie.model.ScreeningPeriodState
import woowacourse.movie.model.mapper.toDomain

class DateSpinnerAdapter(
    private val dateSpinner: Spinner,
    screeningPeriodState: ScreeningPeriodState,
    context: Context
) {
    private val dateAdapter = ArrayAdapter(
        context,
        android.R.layout.simple_spinner_dropdown_item,
        screeningPeriodState.toDomain().getScreeningDates()
    )

    fun initAdapter() {
        dateSpinner.adapter = dateAdapter
    }
}
