package woowacourse.movie.domain.reservation

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

interface ShowtimePolicy {
    fun showtimes(
        date: LocalDate,
        current: LocalDateTime,
    ): List<LocalTime>
}

class DefaultShowtimePolicy : ShowtimePolicy {
    override fun showtimes(
        date: LocalDate,
        current: LocalDateTime,
    ): List<LocalTime> {
        if (date == current.toLocalDate()) {
            if (date.isHoliday) {
                return holidayShowtimes(current.toLocalTime())
            }
            return workingDayShowtimes(current.toLocalTime())
        }

        return showtimes(date)
    }

    private fun showtimes(date: LocalDate): List<LocalTime> {
        if (date.isHoliday) {
            return holidayShowtimes()
        }
        return workingDayShowtimes()
    }

    private val LocalDate.isHoliday: Boolean get() = dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY

    private fun workingDayShowtimes(): List<LocalTime> =
        (WORKING_DAY_START_HOUR until END_HOUR step HOUR_INTERVAL)
            .map { hour: Int ->
                LocalTime.of(
                    hour,
                    0,
                )
            }

    private fun workingDayShowtimes(current: LocalTime): List<LocalTime> =
        (WORKING_DAY_START_HOUR until END_HOUR step HOUR_INTERVAL)
            .map { hour: Int ->
                LocalTime.of(
                    hour,
                    0,
                )
            }.filter { it.isAfter(current) }

    private fun holidayShowtimes(): List<LocalTime> =
        (HOLIDAY_START_HOUR until END_HOUR step HOUR_INTERVAL)
            .map { hour: Int ->
                LocalTime.of(
                    hour,
                    0,
                )
            }

    private fun holidayShowtimes(current: LocalTime): List<LocalTime> =
        (HOLIDAY_START_HOUR until END_HOUR step HOUR_INTERVAL)
            .map { hour: Int ->
                LocalTime.of(
                    hour,
                    0,
                )
            }.filter { it.isAfter(current) }

    companion object {
        const val WORKING_DAY_START_HOUR = 10
        const val HOLIDAY_START_HOUR = 9
        const val HOUR_INTERVAL = 2
        const val END_HOUR = 24
    }
}
