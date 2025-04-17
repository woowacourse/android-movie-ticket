package woowacourse.movie.domain

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

class RunningTimes(
    val currentDate:LocalDate,
    val currentTime: LocalTime
) {
    private fun isWeekdays(targetDay: LocalDate): Boolean {
        val dayOfWeek = targetDay.dayOfWeek
        return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY
    }

    fun runningTimes(targetDay: LocalDate): List<LocalTime> {
        val runningTimes = mutableListOf<LocalTime>()

        val startTime = if (isWeekdays(targetDay)) LocalTime.of(9, 0) else LocalTime.of(10, 0)
        val endTime = if (isWeekdays(targetDay)) LocalTime.of(23, 0) else LocalTime.of(22, 0)

        if (currentTime.isAfter(endTime)) throw IllegalStateException("이미 오늘의 상영이 종료되었습니다.")
        var movieTime = startTime

        while (!movieTime.isAfter(endTime)) {
            if (currentDate == targetDay && !movieTime.isBefore(currentTime)) {
                runningTimes.add(movieTime)
            }
            movieTime = movieTime.plusHours(2)
        }
        return runningTimes.toList()
    }
}