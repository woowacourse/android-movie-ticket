package woowacourse.movie.util

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.R

fun Spinner.setClickListener(
    onClick: (
        parent: AdapterView<*>?,
        view: View?,
        position: Int,
        id: Long
    ) -> Unit = { _, _, _, _ -> },
    onNothing: (
        parent: AdapterView<*>?
    ) -> Unit = {}
) {
    onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {
            onClick(parent, view, position, id)
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
            onNothing(parent)
        }
    }
}

fun <T> Spinner.setDefaultAdapter(itmes: List<T>) {
    val spinnerAdapter = ArrayAdapter(
        this.context,
        R.layout.support_simple_spinner_dropdown_item,
        itmes.toList()
    )
    adapter = spinnerAdapter
}
