package woowacourse.movie.domain.screening

import java.time.LocalDate

data class ScreeningDate(val releaseDate: LocalDate, val endDate: LocalDate) {
    fun schedules(): ScreeningSchedule {
        return releaseDate.spreadDates(endDate)
            .map { DailySchedule(it) }
            .let(::ScreeningSchedule)
    }

    private fun LocalDate.spreadDates(endExclusive: LocalDate): List<LocalDate> {
        return generateSequence(this) { it.plusDays(1) }
            .takeWhile { it in this..endExclusive }
            .toList()
    }
}
