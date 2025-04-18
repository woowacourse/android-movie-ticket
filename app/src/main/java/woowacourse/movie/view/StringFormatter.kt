package woowacourse.movie.view

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object StringFormatter {
    fun dotDateFormat(time: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern(DATE_TIME_DOT_FORMAT)
        return time.format(formatter)
    }

    fun thousandFormat(price: Int): String {
        return THOUSAND_UNIT_FORMAT.format(price).format(price)
    }

    private const val DATE_TIME_DOT_FORMAT = "yyyy.M.d"
    private const val THOUSAND_UNIT_FORMAT = "%,d"
}
