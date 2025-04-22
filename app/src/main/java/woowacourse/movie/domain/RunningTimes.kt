package woowacourse.movie.domain

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class RunningTimes(
    val currentDateTime: LocalDateTime = LocalDateTime.now(),
) {
    private fun isWeekdays(targetDay: LocalDate): Boolean {
        val dayOfWeek = targetDay.dayOfWeek
        return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY
    }

    fun runningTimes(targetDay: LocalDate): List<LocalTime> {
        val startDateTime =
            LocalDateTime.of(
                targetDay,
                if (isWeekdays(targetDay)) LocalTime.of(10, 0) else LocalTime.of(9, 0),
            )
        val endDateTime = targetDay.plusDays(1).atTime(LocalTime.of(0, 0, 0))

        if (currentDateTime.isAfter(endDateTime)) throw IllegalStateException("이미 오늘의 상영이 종료되었습니다.")
        val potentialAndFutureTimes =
            generateSequence(startDateTime) { it.plusHours(2) }
                .takeWhile { it <= endDateTime }
                .filter { if (targetDay == currentDateTime.toLocalDate()) it > currentDateTime else true }
                .toList()
        return potentialAndFutureTimes.map { it.toLocalTime() }
    }
}
