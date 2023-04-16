package woowacourse.movie.ui

import android.content.Context
import woowacourse.movie.R
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateTimeFormatters {

    fun convertToDate(localDate: LocalDate): String = localDate.format(DATE_FORMATTER)

    fun convertToDateTime(localDate: LocalDateTime): String = localDate.format(DATE_TIME_FORMATTER)

    fun convertToDateTildeDate(context: Context, startDate: LocalDate, endDate: LocalDate): String {
        return context.getString(
            R.string.running_date,
            convertToDate(startDate),
            convertToDate(endDate),
        )
    }

    private val DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy.M.d")
    private val DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy.M.d HH:mm")
}
