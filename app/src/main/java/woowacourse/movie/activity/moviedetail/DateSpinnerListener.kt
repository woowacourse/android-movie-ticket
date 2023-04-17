package woowacourse.movie.activity.moviedetail

import android.R
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import java.time.LocalDate
import java.time.LocalTime

class DateSpinnerListener(private val playingTimes: Map<LocalDate, List<LocalTime>>, private val spinnerTime: Spinner) : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, index: Int, p3: Long) {
        val times = playingTimes[playingTimes.keys.sorted()[index]] ?: emptyList()
        spinnerTime.adapter = ArrayAdapter(spinnerTime.context, R.layout.simple_spinner_item, times)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }
}
