package woowacourse.movie.ui

import android.content.Context
import woowacourse.movie.R
import woowacourse.movie.model.Date

object DateUi {
    fun format(
        date: Date,
        context: Context,
    ): String {
        return context.resources.getString(R.string.screening_date)
            .format(date.year, date.month, date.day)
    }
}
