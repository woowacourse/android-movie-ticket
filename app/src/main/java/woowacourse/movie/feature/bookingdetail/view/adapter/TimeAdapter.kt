package woowacourse.movie.feature.bookingdetail.view.adapter

import android.R
import android.content.Context
import android.widget.ArrayAdapter
import woowacourse.movie.domain.model.DateType
import woowacourse.movie.domain.model.MovieTime
import woowacourse.movie.domain.model.MovieTime.Companion.getMovieTimes
import woowacourse.movie.feature.mapper.toUi

class TimeAdapter(
    context: Context,
) : ArrayAdapter<String>(
        context,
        R.layout.simple_spinner_item,
        mutableListOf(),
    ) {
    init {
        setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
    }

    fun updateTimes(dateType: DateType) {
        clear()

        val movieTimes: List<MovieTime> = getMovieTimes(dateType)
        addAll(movieTimes.map { it.toUi().toString() })

        notifyDataSetChanged()
    }
}
