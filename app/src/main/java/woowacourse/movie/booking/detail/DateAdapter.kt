package woowacourse.movie.booking.detail

import android.content.Context
import android.widget.ArrayAdapter
import java.time.LocalDate

class DateAdapter(
    context: Context,
    dates: List<LocalDate>,
) : ArrayAdapter<LocalDate>(
        context,
        android.R.layout.simple_spinner_item,
        dates.toMutableList(),
    ) {
    init {
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }
}
