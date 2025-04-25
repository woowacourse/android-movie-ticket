package woowacourse.movie.view.mapper

import android.icu.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Formatter {
    private val DATE_UI_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    private const val TIME_UI_SUFFIX = "%d:00"
    private val PRICE_UI_FORMATTER = DecimalFormat("#,###")

    @JvmStatic
    fun localDateToUI(date: LocalDate): String = date.format(DATE_UI_FORMATTER)

    @JvmStatic
    fun movieTimeToUI(time: Int): String = TIME_UI_SUFFIX.format(time)

    @JvmStatic
    fun uiToMovieTime(value: String): Int {
        val index = value.indexOf(":")
        return value.substring(0, index).toInt()
    }

    @JvmStatic
    fun priceToUI(price: Int): String = PRICE_UI_FORMATTER.format(price)
}
