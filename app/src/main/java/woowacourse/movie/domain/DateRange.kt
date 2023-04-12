package woowacourse.movie.domain

import java.io.Serializable
import java.time.LocalDate
import java.time.Period

class DateRange(val startDate: LocalDate, val endDate: LocalDate) : Serializable {
    fun toList(): List<LocalDate> =
        (0..Period.between(startDate, endDate).days).map { startDate.plusDays(it.toLong()) }
}
