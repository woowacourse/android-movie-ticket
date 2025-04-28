package woowacourse.movie.booking.detail

import android.content.Context
import android.widget.ArrayAdapter
import java.time.LocalTime

class TimeAdapter(
    context: Context,
    times: List<LocalTime>,
) : ArrayAdapter<LocalTime>(
        context,
        android.R.layout.simple_spinner_item,
        times.toMutableList(),
    ) {
    init {
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }
}
