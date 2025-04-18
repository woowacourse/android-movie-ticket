package woowacourse.movie.view

import android.icu.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object StringFormatter {
    fun periodFormat(
        startDate: LocalDate,
        endDate: LocalDate,
    ): String = PERIOD_FORMAT.format(dotDateFormat(startDate), dotDateFormat(endDate))

    fun dotDateFormat(time: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern(DATE_TIME_DOT_FORMAT)
        return time.format(formatter)
    }

    fun thousandFormat(price: Int): String = DecimalFormat(THOUSAND_UNIT_FORMAT).format(price)

    private const val PERIOD_FORMAT = "%s ~ %s"
    private const val DATE_TIME_DOT_FORMAT = "yyyy.M.d"
    private const val THOUSAND_UNIT_FORMAT = "#,###"
}
