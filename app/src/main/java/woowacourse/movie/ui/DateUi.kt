package woowacourse.movie.ui

import android.content.Context
import woowacourse.movie.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object DateUi {
    fun screeningDateMessage(
        date: LocalDate,
        context: Context,
    ): String {
        return context.resources.getString(R.string.screening_date)
            .format(dateMessage(date))
    }

    fun dateMessage(date: LocalDate): String {
        return date.format(DateTimeFormatter.ofPattern("yyyy.M.d"))
    }
}
