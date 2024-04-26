package woowacourse.movie.model.schedule

import java.time.DayOfWeek
import java.time.LocalDate

class WeekendTimeTable(
    private val date: LocalDate
) : ScreeningTimeTable by RegularTimeTable(
    start = ScreeningTime.of(date,9, 0),
    end = ScreeningTime.of(date,24, 0),
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
