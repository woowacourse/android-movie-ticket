package woowacourse.movie.movieReservation

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import model.ScreeningModel
import movie.ScreeningDate
import woowacourse.movie.R
import java.time.LocalDate

class ReservationDateSpinner(
    private val view: View,
    private val onDateSelected: (LocalDate) -> Unit,
) {
    private val dateSpinner: Spinner = view.findViewById(R.id.reservation_screening_date_spinner)

    val selectedDate: LocalDate
        get() = LocalDate.parse(dateSpinner.selectedItem.toString())

    init {
        dateSpinner.onItemSelectedListener = DateSpinnerListener()
    }

    fun save(outState: Bundle) {
        outState.putInt(KEY_DATE, dateSpinner.selectedItemPosition)
    }

    fun load(savedInstanceState: Bundle) {
        dateSpinner.setSelection(savedInstanceState.getInt(KEY_DATE))
    }

    fun initDateSpinner(screeningModel: ScreeningModel) {
        val dateList = ScreeningDate.getScreeningDate(screeningModel.startDate, screeningModel.endDate)
        dateSpinner.adapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_item, dateList)
    }

    inner class DateSpinnerListener : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) = Unit
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            onDateSelected(selectedDate)
        }
    }

    companion object {
        private const val KEY_DATE = "dateSpinner"
    }
}
