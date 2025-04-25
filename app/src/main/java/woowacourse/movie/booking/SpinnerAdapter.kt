package woowacourse.movie.booking

import android.content.Context
import android.widget.ArrayAdapter
import android.widget.Spinner
import woowacourse.movie.R

object SpinnerAdapter {
    fun bind(
        context: Context,
        spinner: Spinner,
        items: List<String>,
    ) {
        val adapter = ArrayAdapter(context, R.layout.spinner_item, items)
        adapter.setDropDownViewResource(R.layout.spinner_item)
        spinner.adapter = adapter
    }
}
