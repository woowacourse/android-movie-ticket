package woowacourse.movie.movieReservation

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import movie.ScreeningDate
import woowacourse.movie.R
import java.time.LocalDate
import java.time.LocalTime

class ReservationTimeSpinner(view: View) {
    private var date = LocalDate.MIN

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

    fun load(savedInstanceState: Bundle) {
        savedInstanceState.getStringArrayList(KEY_TIME_LIST)?.let { newTimeList ->
            updateTimeList(newTimeList.map { LocalTime.parse(it) })
        }

        date = LocalDate.parse(savedInstanceState.getString(KEY_DATE))
    }

    fun save(outState: Bundle) {
        outState.putString(KEY_DATE, date.toString())
        outState.putStringArrayList(KEY_TIME_LIST, timeList.map { it.toString() }.toCollection(ArrayList()))
    }

    fun initTimeSpinner(date: LocalDate) {
        if (date == this.date) return

        this.date = date
        timeSpinner.setSelection(0)
        updateTimeList(ScreeningDate.getScreeningTimes(date))
    }

    private fun updateTimeList(newTimeList: List<LocalTime>) {
        timeList.clear()
        timeList.addAll(newTimeList)
        arrayAdapter.notifyDataSetChanged()
    }

    companion object {
        private const val KEY_DATE = "timeSpinnerDate"
        private const val KEY_TIME_LIST = "timeList"
    }
}
