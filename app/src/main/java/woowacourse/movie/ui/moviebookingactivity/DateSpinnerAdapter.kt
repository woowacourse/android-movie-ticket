package woowacourse.movie.ui.moviebookingactivity

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import woowacourse.movie.model.ScreeningPeriodState
import woowacourse.movie.model.mapper.toDomain
import java.time.LocalDate

class DateSpinnerAdapter(
    private val dateSpinner: Spinner,
    private val transferSelectedDate: (LocalDate) -> Unit,
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
        setOnItemSelectedListener()
    }

    private fun setOnItemSelectedListener() {
        dateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                transferSelectedDate(dateSpinner.selectedItem as LocalDate)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {}
        }
    }
}
