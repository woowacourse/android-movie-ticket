package woowacourse.movie

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object LocalDateHelper {
    @JvmStatic
    fun LocalDate.toDotFormat(): String {
        return this.format(DateTimeFormatter.ofPattern("yyyy.MM.dd"))
    }

    @JvmStatic
    fun LocalDateTime.toDotFormat(): String {
        return this.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"))
    }

    @JvmStatic
    fun LocalTime.toDotFormat(): String {
        return this.format(DateTimeFormatter.ofPattern("HH:mm"))
    }

    @JvmStatic
    fun String.toLocalDateFromDot(): LocalDate {
        val (year, month, day) = this.split(".").map { it.toInt() }
        return LocalDate.of(year, month, day)
    }

    @JvmStatic
    fun String.toLocalDateFromDash(): LocalDate {
        val (year, month, day) = this.split("-").map { it.toInt() }
        return LocalDate.of(year, month, day)
    }
}

