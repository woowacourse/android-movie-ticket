package woowacourse.movie.activity.booking

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner

class SpinnerSelectedListener(
    private val spinner: Spinner,
    private val currentItemProvider: () -> String,
    private val onItemChanged: (String) -> Unit,
) : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long,
    ) {
        val selectedItem = spinner.getItemAtPosition(position).toString()
        if (selectedItem != currentItemProvider()) {
            onItemChanged(selectedItem)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) = Unit
}
