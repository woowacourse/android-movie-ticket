package woowacourse.movie.model.schedule

class WeekendTimeTable(
    private val screeningDate: ScreeningDate
) : ScreeningTimeTable by RegularTimeTable(
    start = ScreeningTime.of(screeningDate.date, 9, 0),
    end = ScreeningTime.of(screeningDate.date, 24, 0),
    2
) {
    init {
        require(screeningDate.isWeekend()) {
            "WeekendTimeTable 에러. 주말이 아닙니다."
        }
    }
}
