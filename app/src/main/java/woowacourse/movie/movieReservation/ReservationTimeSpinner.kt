package woowacourse.movie.movieReservation

import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import woowacourse.movie.R
import java.time.LocalTime

class ReservationTimeSpinner(
    view: View,
) {
    private val timeList: MutableList<LocalTime> = mutableListOf()

    private val arrayAdapter = ArrayAdapter(
        view.context,
        android.R.layout.simple_spinner_item,
        timeList,
    )

    private val timeSpinner: Spinner =
        view.findViewById<Spinner>(R.id.reservation_screening_time_spinner)
            .apply { adapter = arrayAdapter }

    val selectedTime: LocalTime
        get() = timeList[timeSpinner.selectedItemPosition]

    fun initTimeSpinner(timeList: List<LocalTime>) {
        updateTimeList(timeList)
    }

    private fun updateTimeList(newTimeList: List<LocalTime>) {
        timeList.clear()
        timeList.addAll(newTimeList)
        arrayAdapter.notifyDataSetChanged()
    }
}
