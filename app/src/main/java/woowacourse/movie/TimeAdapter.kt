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

        val times: List<Time> =
            when (dateType) {
                DateType.WEEKDAY -> Time.weekdaysTimes
                DateType.WEEKEND -> Time.weekendsTimes
            }

        addAll(times.map { it.toString() })

        notifyDataSetChanged()
    }
}
