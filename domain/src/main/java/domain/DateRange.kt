package domain

import java.time.LocalDate
import java.time.Period

class DateRange(val startDate: LocalDate, val endDate: LocalDate) {
    fun toList(): List<LocalDate> =
        (0..Period.between(startDate, endDate).days).map { startDate.plusDays(it.toLong()) }
}
