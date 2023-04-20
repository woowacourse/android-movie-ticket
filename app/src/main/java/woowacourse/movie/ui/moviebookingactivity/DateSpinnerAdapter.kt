package woowacourse.movie.ui.moviebookingactivity

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import woowacourse.movie.domain.datetime.ScreeningPeriod
import java.time.LocalDate

class DateSpinnerAdapter(
    dateSpinner: Spinner,
    itemSelectedListener: (LocalDate) -> Unit,
    screeningPeriod: ScreeningPeriod,
    context: Context
) {
    private val dateAdapter = ArrayAdapter(
        context,
        android.R.layout.simple_spinner_dropdown_item,
        screeningPeriod.getScreeningDates()
    )

    init {
        initAdapter(dateSpinner, itemSelectedListener)
    }

    fun initAdapter(
        dateSpinner: Spinner,
        transferSelectedDate: (LocalDate) -> Unit
    ) {
        dateSpinner.adapter = dateAdapter
        setOnItemSelectedListener(dateSpinner, transferSelectedDate)
    }

    private fun setOnItemSelectedListener(
        dateSpinner: Spinner,
        transferSelectedDate: (LocalDate) -> Unit
    ) {
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
