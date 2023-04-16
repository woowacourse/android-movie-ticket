package woowacourse.movie.view.widget

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner

class SaveStateSpinner(override val saveStateKey: String, val spinner: Spinner) : SaveState {

    override fun save(outState: Bundle) {
        outState.putInt(saveStateKey, spinner.selectedItemPosition)
    }

    override fun load(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            spinner.setSelection(savedInstanceState.getInt(saveStateKey))
        }
    }

    fun initSpinner(data: List<*>) {
        val dateAdapter = ArrayAdapter(spinner.context, android.R.layout.simple_spinner_dropdown_item, data)
        spinner.adapter = dateAdapter
    }
}
