package woowacourse.movie.movie

import woowacourse.movie.ticket.Seat
import woowacourse.movie.ticket.Ticket
import java.time.DayOfWeek.FRIDAY
import java.time.DayOfWeek.MONDAY
import java.time.DayOfWeek.SATURDAY
import java.time.DayOfWeek.SUNDAY
import java.time.DayOfWeek.THURSDAY
import java.time.DayOfWeek.TUESDAY
import java.time.DayOfWeek.WEDNESDAY
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class Movie(
    val id: Long,
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Int,
    val description: String,
) {
    val screeningDates: List<LocalDate>
        get() {
            val numberOfDays: Int = startDate.until(endDate).days
            return (0..numberOfDays).map { (startDate.plusDays(it.toLong())) }
        }

    fun reserve(dateTime: LocalDateTime, seat: Seat): Ticket {
        val date = dateTime.toLocalDate()
        if (date !in screeningDates) throw IllegalArgumentException("상영 기간이 아닙니다.")
        return Ticket(id, dateTime, seat)
    }

    companion object {
        private const val WEEKDAY_START = 10
        private const val WEEKEND_START = 9
        private const val END = 23
        private const val TIME_STEP = 2
        fun getScreeningTime(date: LocalDate): List<LocalTime> = when (date.dayOfWeek) {
            MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY -> getHours(WEEKDAY_START, END, TIME_STEP)
            SATURDAY, SUNDAY -> getHours(WEEKEND_START, END, TIME_STEP)
            null -> emptyList()
        }

        private fun getHours(
            startTime: Int,
            endTime: Int,
            timeStep: Int,
        ): List<LocalTime> {
            return (startTime..endTime step timeStep).map { LocalTime.of(it, 0) }
        }
    }
}
