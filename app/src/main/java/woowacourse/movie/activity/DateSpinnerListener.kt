package woowacourse.movie.activity

import android.R
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import woowacourse.movie.model.PlayingTimes
import java.time.LocalDate

class DateSpinnerListener(private val playingTimes: PlayingTimes, private val dates: List<LocalDate>, private val spinnerTime: Spinner) : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(adapterView: AdapterView<*>?, view: View?, index: Int, p3: Long) {
        val times = playingTimes.times[dates[index]] ?: emptyList()
        spinnerTime.adapter = ArrayAdapter(spinnerTime.context, R.layout.simple_spinner_item, times)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }
}
