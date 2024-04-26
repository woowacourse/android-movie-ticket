package woowacourse.movie.domain.screening

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class DailySchedule(val date: LocalDate) {
    val times: List<LocalDateTime> =
        if (date.dayOfWeek == DayOfWeek.SATURDAY || date.dayOfWeek == DayOfWeek.SUNDAY) {
            getLocalDateTimes(9)
        } else {
            getLocalDateTimes(10)
        }

    private fun getLocalDateTimes(startHour: Int): List<LocalDateTime> {
        val range = (startHour..24 step 2)
        return range.map { hour ->
            if (hour == 24) LocalDateTime.of(date, LocalTime.of(0, 0, 0))
            else LocalDateTime.of(date, LocalTime.of(hour, 0, 0))
        }.toList()
    }
}
