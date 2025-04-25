package woowacourse.movie.feature.bookingdetail.view.adapter

import android.content.Context
import android.widget.ArrayAdapter

class TimeAdapter(
    context: Context,
    times: List<String>,
) : ArrayAdapter<String>(
        context,
        android.R.layout.simple_spinner_item,
        times.toMutableList(),
    ) {
    init {
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }

    fun updateTimes(times: List<String>) {
        clear()
        addAll(times)
        notifyDataSetChanged()
    }
}
