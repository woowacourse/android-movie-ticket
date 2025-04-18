package woowacourse.movie.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Parcelize
class RunningTimes(
    val currentDateTime: LocalDateTime = LocalDateTime.now(),
) : Parcelable {
    private fun isWeekdays(targetDay: LocalDate): Boolean {
        val dayOfWeek = targetDay.dayOfWeek
        return dayOfWeek != DayOfWeek.SATURDAY && dayOfWeek != DayOfWeek.SUNDAY
    }

    fun runningTimes(targetDay: LocalDate): List<LocalTime> {
        val startDateTime =
            LocalDateTime.of(
                targetDay,
                if (isWeekdays(targetDay)) LocalTime.of(9, 0) else LocalTime.of(10, 0),
            )
        val endDateTime =
            LocalDateTime.of(
                targetDay,
                if (isWeekdays(targetDay)) LocalTime.of(23, 0) else LocalTime.of(22, 0),
            )

        if (currentDateTime.isAfter(endDateTime)) throw IllegalStateException("이미 오늘의 상영이 종료되었습니다.")
        val potentialAndFutureTimes =
            generateSequence(startDateTime) { it.plusHours(2) }
                .takeWhile { !it.isAfter(endDateTime) }
                .filter { it.isAfter(currentDateTime) || it.isEqual(currentDateTime) }
                .toList()
        return potentialAndFutureTimes.map { it.toLocalTime() }
    }
}
