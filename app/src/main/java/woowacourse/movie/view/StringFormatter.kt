package woowacourse.movie.view

import android.icu.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object StringFormatter {
    fun dotDateFormat(time: LocalDate): String {
        val formatter = DateTimeFormatter.ofPattern(DATE_TIME_DOT_FORMAT)
        return time.format(formatter)
    }

    fun thousandFormat(price: Int): String {
        return DecimalFormat(THOUSAND_UNIT_FORMAT).format(price)
    }

    private const val DATE_TIME_DOT_FORMAT = "yyyy.M.d"
    private const val THOUSAND_UNIT_FORMAT = "#,###"
}
