package woowacourse.movie.model

import java.time.DayOfWeek
import java.time.LocalDate

class WeekendTimeTable(
    private val date: LocalDate
) : ScreeningTimeTable by RegularTimeTable(
    start = ScreeningTime(date.atTime(9, 0)),
    end = ScreeningTime(date.plusDays(1).atTime(0, 0)),
    2
) {
    init {
        require(
            date.dayOfWeek == DayOfWeek.SATURDAY ||
                    date.dayOfWeek == DayOfWeek.SUNDAY
        ) {
            "timetable 에러. 주말이 아닙니다."
        }
    }
}
