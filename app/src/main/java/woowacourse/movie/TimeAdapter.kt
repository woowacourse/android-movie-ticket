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

        when (dateType) {
            DateType.WEEKDAY -> weekdaysTimes
            DateType.WEEKEND -> weekendsTimes
        }

        notifyDataSetChanged()
    }

    companion object {
        val weekdaysTimes: List<String> =
            listOf(
                "09:00",
                "11:00",
                "13:00",
                "15:00",
                "17:00",
                "19:00",
                "21:00",
                "23:00",
            )

        val weekendsTimes: List<String> =
            listOf(
                "10:00",
                "12:00",
                "14:00",
                "16:00",
                "18:00",
                "20:00",
                "22:00",
                "24:00",
            )
    }
}
