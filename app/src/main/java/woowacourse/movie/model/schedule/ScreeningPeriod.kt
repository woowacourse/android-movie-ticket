package woowacourse.movie.model.schedule

import java.time.temporal.ChronoUnit

data class ScreeningPeriod(val start: ScreeningDate, val end: ScreeningDate) {
    val dates by lazy {
        val startDate = start.date
        val endDate = end.date
        val days = startDate.until(endDate, ChronoUnit.DAYS).toInt()
        generateSequence(startDate) {
            it.plusDays(1)
        }.map { ScreeningDate(it) }.take(days + 1).toList()
    }
}


