package woowacourse.movie.movieReservation

import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import movie.ScreeningDate
import woowacourse.movie.R
import java.time.LocalDate
import java.time.LocalTime

class ReservationTimeSpinner(view: View) {
    private val timeList = mutableListOf<LocalTime>()

    private val arrayAdapter = ArrayAdapter(
        view.context,
        android.R.layout.simple_spinner_item,
        timeList,
    )

    private val timeSpinner: Spinner =
        view.findViewById<Spinner>(R.id.reservation_screening_time_spinner)
            .apply { adapter = arrayAdapter }

    val selectedTime: LocalTime
        get() = LocalTime.parse(timeSpinner.selectedItem.toString())

    fun initTimeSpinner(date: LocalDate) {
        updateTimeList(ScreeningDate.getScreeningTimes(date))
    }

    private fun updateTimeList(newTimeList: List<LocalTime>) {
        timeList.clear()
        timeList.addAll(newTimeList)
        arrayAdapter.notifyDataSetChanged()
    }
}
