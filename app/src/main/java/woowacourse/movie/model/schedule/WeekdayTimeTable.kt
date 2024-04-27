package woowacourse.movie.model.schedule

class WeekdayTimeTable(
    private val screeningDate: ScreeningDate
) : ScreeningTimeTable by RegularTimeTable(
    start = ScreeningTime.of(screeningDate.date, 10, 0),
    end = ScreeningTime.of(screeningDate.date, 24, 0),
    2
) {
    init {
        require(screeningDate.isWeekday()) {
            "WeekdayTimeTable 에러. 평일이 아닙니다."
        }
    }
}
