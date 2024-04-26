package woowacourse.movie.model

import java.time.DayOfWeek
import java.time.LocalDate

class WeekdayTimeTable(
    private val date: LocalDate
) : ScreeningTimeTable by RegularTimeTable(
    start = ScreeningTime.of(date, 10, 0),
    end = ScreeningTime.of(date, 24, 0),
    2
) {
    init {
        require(
            !(date.dayOfWeek == DayOfWeek.SATURDAY && date.dayOfWeek == DayOfWeek.SUNDAY)
        ) {
            "WeekdayTimeTable 에러. 평일이 아닙니다."
        }
    }
}
