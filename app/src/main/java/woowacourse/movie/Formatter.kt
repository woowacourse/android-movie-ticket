package woowacourse.movie

import android.icu.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Formatter {
    fun localDateToUI(date: LocalDate): String = date.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))

    fun movieTimeToUI(time: Int): String = "$time:00"

    fun priceToUI(price: Int): String = THOUSAND_COMMA.format(price)

    companion object {
        private val THOUSAND_COMMA = DecimalFormat("#,###")
    }
}
