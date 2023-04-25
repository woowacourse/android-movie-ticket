package woowacourse.movie.listener

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.domain.model.model.PlayingTimes
import java.time.LocalDate

class DateSpinnerListener(
    private val playingTimes: PlayingTimes,
    private val dates: List<LocalDate>,
    private val spinnerTime: Spinner
) : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(
        adapterView: AdapterView<*>?,
        view: View?,
        index: Int,
        p3: Long
    ) {
        val times = playingTimes.getTimes(dates[index])
        spinnerTime.adapter =
            ArrayAdapter(spinnerTime.context, android.R.layout.simple_spinner_item, times)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) = Unit
}
