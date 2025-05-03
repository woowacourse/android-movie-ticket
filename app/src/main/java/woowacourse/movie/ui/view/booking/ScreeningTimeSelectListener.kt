package woowacourse.movie.ui.view.booking

import android.view.View
import android.widget.AdapterView

class ScreeningTimeSelectListener(
    val onSelect: (String) -> Unit,
) : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long,
    ) {
        onSelect(parent?.getItemAtPosition(position) as String)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        onSelect(parent?.getItemAtPosition(0) as String)
    }
}
