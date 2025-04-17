package woowacourse.movie

import android.icu.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class Formatter {
    fun localDateToUI(date: LocalDate): String = date.format(DATE_UI_FORMATTER)

    fun movieTimeToUI(time: Int): String = TIME_UI_SUFFIX.format(time)

    fun priceToUI(price: Int): String = PRICE_UI_FORMATTER.format(price)

    fun uiToLocalDate(time: String): LocalDate {
        val year = time.slice(YEAR_RANGE).toInt()
        val month = time.slice(MONTH_RANGE).toInt()
        val date = time.slice(DAY_RANGE).toInt()
        return LocalDate.of(year, month, date)
    }

    companion object {
        private val DATE_UI_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd")
        private const val TIME_UI_SUFFIX = "%d:00"
        private val PRICE_UI_FORMATTER = DecimalFormat("#,###")
        private val YEAR_RANGE = 0..3
        private val MONTH_RANGE = 5..6
        private val DAY_RANGE = 8..9
    }
}
