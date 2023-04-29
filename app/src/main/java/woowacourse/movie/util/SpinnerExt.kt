package woowacourse.movie.util

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.R

fun Spinner.setClickListener(
    onClick: (position: Int) -> Unit = { },
    onNothing: (parent: AdapterView<*>?) -> Unit = {}
) {
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            onClick(position)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            onNothing(parent)
        }
    }
}

fun <T> Spinner.setDefaultAdapter(items: List<T>) {
    val spinnerAdapter = ArrayAdapter(
        this.context,
        R.layout.support_simple_spinner_dropdown_item,
        items.toList()
    )
    adapter = spinnerAdapter
}
