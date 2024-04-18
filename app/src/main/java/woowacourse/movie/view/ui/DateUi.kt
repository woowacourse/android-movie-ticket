package woowacourse.movie.view.ui

import android.content.Context
import woowacourse.movie.R
import woowacourse.movie.model.data.dto.Date

object DateUi {
    fun screeningDateMessage(
        date: Date,
        context: Context,
    ): String {
        return context.resources.getString(R.string.screening_date)
            .format(date.year, date.month, date.day)
    }

    fun dateMessage(
        date: Date,
        context: Context,
    ): String {
        return context.resources.getString(R.string.date)
            .format(date.year, date.month, date.day)
    }
}
