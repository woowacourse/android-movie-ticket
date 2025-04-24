package woowacourse.movie.feature.bookingdetail.view.adapter

import android.R
import android.content.Context
import android.widget.ArrayAdapter
import woowacourse.movie.feature.model.MovieDateUiModel

class DateAdapter(
    context: Context,
    dates: List<MovieDateUiModel>,
) : ArrayAdapter<String>(
        context,
        R.layout.simple_spinner_item,
        dates.map { it.toString() },
    ) {
    init {
        setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
    }
}
