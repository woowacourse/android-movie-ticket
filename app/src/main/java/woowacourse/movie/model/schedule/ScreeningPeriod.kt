package woowacourse.movie.model.schedule

data class ScreeningPeriod(val start: ScreeningDate, val end: ScreeningDate) {
    val dates by lazy { start..end }
}


