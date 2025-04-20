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
        val runningTimes = mutableListOf<LocalTime>()

        val startTime = if (isWeekdays(targetDay)) LocalTime.of(9, 0) else LocalTime.of(10, 0)
        val endTime = if (isWeekdays(targetDay)) LocalTime.of(23, 0) else LocalTime.of(22, 0)
        val startDateTime = LocalDateTime.of(targetDay, startTime)
        val endDateTime = LocalDateTime.of(targetDay, endTime)


        if (currentDateTime.isAfter(endDateTime)) throw IllegalStateException(ERROR_TODAY_MOVIE_FINISH)
        var movieTime = startDateTime

        while (!movieTime.isAfter(endDateTime)) {
            if (currentDateTime.toLocalDate() == targetDay && !movieTime.isBefore(currentDateTime)) {
                movieTime = movieTime.plusHours(2)
                continue
            }
            runningTimes.add(movieTime.toLocalTime())
            movieTime = movieTime.plusHours(2)

        }
        return runningTimes.toList()
    }

    companion object {
        private const val ERROR_TODAY_MOVIE_FINISH = "이미 오늘의 상영이 종료되었습니다."
    }
}
