package woowacourse.movie.domain.model.booking

import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

class ScreeningTimeSpinner(
    private val spinner: Spinner,
) {
    fun updateAdapter(times: List<String>) {
        spinner.adapter =
            ArrayAdapter(
                spinner.context,
                android.R.layout.simple_spinner_item,
                times,
            )
    }

    fun setOnItemSelectedListener(listener: AdapterView.OnItemSelectedListener) {
        spinner.onItemSelectedListener = listener
    }

    fun setSelect(position: Int) {
        spinner.setSelection(position)
    }
}
