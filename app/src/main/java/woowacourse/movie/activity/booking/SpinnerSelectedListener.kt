package woowacourse.movie.activity.booking

import android.view.View
import android.widget.AdapterView

class SpinnerSelectedListener(
    private val currentPositionProvider: () -> Int,
    private val onChanged: (Int) -> Unit,
) : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long,
    ) {
        if (position != currentPositionProvider()) {
            onChanged(position)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}
