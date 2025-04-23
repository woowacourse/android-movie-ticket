package woowacourse.movie.presentation.bookingdetail.adapter

import android.content.Context
import android.widget.ArrayAdapter
import java.time.LocalDate

class DateAdapter(
    context: Context,
    startDate: LocalDate,
    endDate: LocalDate,
) : ArrayAdapter<LocalDate>(
        context,
        android.R.layout.simple_spinner_item,
        mutableListOf<LocalDate>().apply {
            var date = startDate
            while (date <= endDate) {
                add(date)
                date = date.plusDays(1)
            }
        },
    ) {
    init {
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }
}
