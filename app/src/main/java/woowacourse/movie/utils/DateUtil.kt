package woowacourse.movie.utils

import android.content.Context
import woowacourse.movie.R
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class DateUtil(private val context: Context) {
    fun getDateRange(startDate: LocalDate, endDate: LocalDate): String {
        return context.getString(R.string.movie_release_date).format(getDate(startDate), getDate(endDate))
    }

    fun getDate(date: LocalDate): String {
        return date.format(dateFormatter)
    }

    fun getTime(time: LocalTime): String {
        return time.format(timeFormatter)
    }

    companion object {
        private val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        private val timeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")
    }
}
