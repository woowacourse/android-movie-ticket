package woowacourse.movie.domain.model.booking

import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner

class ScreeningDateSpinner(
    private val spinner: Spinner,
    dates: List<String>,
) {
    init {
        spinner.adapter =
            ArrayAdapter(
                spinner.context,
                android.R.layout.simple_spinner_item,
                dates,
            )
    }

    fun setOnItemSelectedListener(listener: AdapterView.OnItemSelectedListener) {
        spinner.onItemSelectedListener = listener
    }

    fun setSelect(position: Int) {
        spinner.setSelection(position)
    }
}
