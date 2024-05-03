package woowacourse.movie.domain.screening

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

class BasicScreeningScheduleSystem : ScreeningScheduleSystem {
    override fun getSchedules(
        releaseDate: LocalDate,
        endDate: LocalDate,
    ): ScreeningSchedule {
        return releaseDate.spreadDates(endDate)
            .map { DailySchedule(it, getScreeningTimes(it)) }
            .let(::ScreeningSchedule)
    }

    private fun LocalDate.spreadDates(endExclusive: LocalDate): List<LocalDate> {
        return generateSequence(this) { it.plusDays(1) }
            .takeWhile { it in this..endExclusive }
            .toList()
    }

    override fun getScreeningTimes(date: LocalDate): List<LocalTime> =
        if (date.dayOfWeek == DayOfWeek.SATURDAY || date.dayOfWeek == DayOfWeek.SUNDAY) {
            createTwoHourIntervalTimes(9)
        } else {
            createTwoHourIntervalTimes(10)
        }

    private fun createTwoHourIntervalTimes(startHour: Int): List<LocalTime> {
        val range = (startHour..24 step 2)
        return range.map { hour ->
            if (hour == 24) {
                LocalTime.of(0, 0, 0)
            } else {
                LocalTime.of(hour, 0, 0)
            }
        }.toList()
    }
}
