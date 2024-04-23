package woowacourse.movie

import java.time.LocalDate

object MovieUtils {
    fun convertPeriodFormat(period: List<LocalDate>): String {
        val start = period.first().toString()
        val end = period.last().toString()
        return "%s~%s".format(start, end)
    }
}
