package woowacourse.movie.model

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime

class MovieTime {
    var selectedTime: Int = 0
        private set

    private val weekdayTime: List<Int> =
        (WEEKDAY_SCREENING_TIME_RANGE).step(SCREENING_INTERVAL_TIME).toList()

    private val weekendTime: List<Int> =
        (WEEKEND_SCREENING_TIME_RANGE).step(SCREENING_INTERVAL_TIME).toList()

    fun getTimeTable(
        now: LocalDateTime,
        selectedDate: LocalDate,
    ): List<Int> {
        val times: List<Int>
        if (now.toLocalDate() == selectedDate) {
            times =
                when (now.dayOfWeek) {
                    DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY ->
                        weekdayTime.timeTable(
                            now.hour,
                        )

                    DayOfWeek.SATURDAY, DayOfWeek.SUNDAY -> weekendTime.timeTable(now.hour)

                    null -> emptyList()
                }
        } else {
            times =
                when (selectedDate.dayOfWeek) {
                    DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY ->
                        weekdayTime

                    DayOfWeek.SATURDAY, DayOfWeek.SUNDAY -> weekendTime
                    null -> emptyList()
                }
        }

        return times
    }

    fun updateTime(selectedTime: Int) {
        this.selectedTime = selectedTime
    }

    private fun List<Int>.timeTable(nowHour: Int): List<Int> {
        forEachIndexed { index, time ->
            if (time > nowHour) {
                return slice(index..<size)
            }
        }
        return emptyList()
    }

    companion object {
        private val WEEKDAY_SCREENING_TIME_RANGE = 10..24
        private val WEEKEND_SCREENING_TIME_RANGE = 9..24
        private const val SCREENING_INTERVAL_TIME = 2
    }
}
