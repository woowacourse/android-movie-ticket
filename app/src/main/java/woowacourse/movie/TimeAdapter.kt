package woowacourse.movie

import android.content.Context
import android.widget.ArrayAdapter

class TimeAdapter(
    context: Context,
) : ArrayAdapter<String>(
        context,
        android.R.layout.simple_spinner_item,
        mutableListOf(),
    ) {
    init {
        setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
    }

    fun updateTimes(dateType: DateType) {
        clear()

        val movieTimes: List<MovieTime> =
            when (dateType) {
                DateType.WEEKDAY -> MovieTime.weekdaysMovieTimes
                DateType.WEEKEND -> MovieTime.weekendsMovieTimes
            }

        addAll(movieTimes.map { it.toString() })

        notifyDataSetChanged()
    }
}
