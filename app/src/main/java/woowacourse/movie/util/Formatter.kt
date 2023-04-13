package woowacourse.movie.util

import java.text.DecimalFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object Formatter {
    private const val DATE_FORMAT = "YYYY.M.d"
    private const val TIME_FORMAT = "HH:mm"
    private val dateFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT)
    private val timeFormatter = DateTimeFormatter.ofPattern(TIME_FORMAT)
    private val decimalFormat = DecimalFormat("#,###")

    fun dateFormat(date: LocalDate): String = dateFormatter.format(date)
    fun timeFormat(time: LocalTime): String = timeFormatter.format(time)
    fun decimalFormat(number: Int): String = decimalFormat.format(number)
}
