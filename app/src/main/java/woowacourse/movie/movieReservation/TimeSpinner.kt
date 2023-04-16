package woowacourse.movie.movieReservation

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import movie.ScreeningDate
import woowacourse.movie.R
import java.time.LocalDate
import java.time.LocalTime

class TimeSpinner(
    private val view: View,
) {
    private val timeSpinner: Spinner = view.findViewById(R.id.reservation_screening_time_spinner)

    fun load(outState: Bundle) {
        timeSpinner.setSelection(outState.getInt(KEY_TIME))
    }

    fun save(outState: Bundle) {
        outState.putInt(KEY_TIME, timeSpinner.selectedItemPosition)
    }

    fun initTimeSpinner(date: LocalDate) {
        val timeList = ScreeningDate.getScreeningTime(date)
        timeSpinner.adapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_item, timeList)
    }

    fun getSelectedTime(): LocalTime = LocalTime.parse(timeSpinner.selectedItem.toString())

    companion object {
        private const val KEY_TIME = "timeSpinner"
    }
}
