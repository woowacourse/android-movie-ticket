package woowacourse.movie.model

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalDateTime

class MovieTime {
    var selectedTime: Int = 0
        private set

    private val weekdayTime: List<Int> = (10..24).step(2).toList()

    private val weekendTime: List<Int> = (9..24).step(2).toList()

    fun getTimeTable(
        now: LocalDateTime,
        selectedDate: LocalDate,
    ): List<Int> {
        var times: List<Int>
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
}
