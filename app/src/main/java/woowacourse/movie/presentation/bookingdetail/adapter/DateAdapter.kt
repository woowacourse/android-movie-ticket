package woowacourse.movie.presentation.bookingdetail.adapter

import android.content.Context
import android.widget.ArrayAdapter
import woowacourse.movie.presentation.model.MovieDateUiModel

class DateAdapter(
    context: Context,
    dates: List<MovieDateUiModel>,
) : ArrayAdapter<String>(
        context,
        android.R.layout.simple_spinner_item,
        dates.map { it.toString() },
    ) {
    init {
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }
}
