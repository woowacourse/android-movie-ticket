package woowacourse.movie.movieReservation

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import woowacourse.movie.R
import java.time.LocalDate

class ReservationDateSpinner(
    view: View,
    dateList: List<LocalDate>,
    onDateSelected: (LocalDate) -> Unit,
) {
    private val dateList: List<LocalDate> = dateList.toMutableList()
    private val dateSpinner: Spinner = view.findViewById(R.id.reservation_screening_date_spinner)

    val selectedDate: LocalDate
        get() = dateList[dateSpinner.selectedItemPosition]

    init {
        dateSpinner.adapter = ArrayAdapter(
            view.context,
            android.R.layout.simple_spinner_item,
            dateList,
        )
        dateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) = Unit
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                onDateSelected(selectedDate)
            }
        }
    }
}
