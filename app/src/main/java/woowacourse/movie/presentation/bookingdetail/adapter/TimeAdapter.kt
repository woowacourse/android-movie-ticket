package woowacourse.movie.presentation.bookingdetail.adapter

import android.content.Context
import android.widget.ArrayAdapter
import woowacourse.movie.domain.model.DateType
import woowacourse.movie.domain.model.MovieTime
import woowacourse.movie.domain.model.MovieTime.Companion.getMovieTimes
import woowacourse.movie.presentation.mapper.toUi

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

        val movieTimes: List<MovieTime> = getMovieTimes(dateType)
        addAll(movieTimes.map { it.toUi().toString() })

        notifyDataSetChanged()
    }
}
