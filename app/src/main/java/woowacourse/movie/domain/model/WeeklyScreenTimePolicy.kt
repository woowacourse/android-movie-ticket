package woowacourse.movie.domain.model

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

class WeeklyScreenTimePolicy : ScreenTimePolicy {
    override fun screeningTimes(date: LocalDate): List<LocalTime> {
        return if (date.dayOfWeek in DayOfWeek.MONDAY..DayOfWeek.FRIDAY) {
            WEEKDAY_SCREEN_TIME
        } else {
            WEEKEND_SCREEN_TIME
        }
    }

    companion object {
        val WEEKDAY_SCREEN_TIME =
            listOf(
                LocalTime.of(9, 0),
                LocalTime.of(11, 0),
                LocalTime.of(13, 0),
                LocalTime.of(15, 0),
                LocalTime.of(17, 0),
                LocalTime.of(19, 0),
                LocalTime.of(21, 0),
                LocalTime.of(23, 0),
            )

        val WEEKEND_SCREEN_TIME =
            listOf(
                LocalTime.of(10, 0),
                LocalTime.of(12, 0),
                LocalTime.of(14, 0),
                LocalTime.of(16, 0),
                LocalTime.of(18, 0),
                LocalTime.of(20, 0),
                LocalTime.of(22, 0),
            )
    }
}
