package woowacourse.movie.util

import java.text.DecimalFormat
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object Formatter {
    fun simpleDateFormat(date: LocalDate): String = date.format(DateTimeFormatter.ofPattern("yyyy.M.d"))

    fun simpleTimeFormat(time: LocalTime): String {
        return if (time == LocalTime.MIDNIGHT) {
            "24:00"
        } else {
            time.format(DateTimeFormatter.ofPattern("HH:mm"))
        }
    }

    fun moneyFormat(amount: Int): String = DecimalFormat("#,###").format(amount)
}
