package woowacourse.movie.model.schedule

data class ScreeningPeriod(val start: ScreeningDate, val end: ScreeningDate) {
    init{
        require(start.date <= end.date) { "상영기간 에러. 시작 날짜가 끝 날짜보다 먼저여야 합니다."}
    }
    val dates = start..end
}


