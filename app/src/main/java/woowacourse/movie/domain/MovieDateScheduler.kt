package woowacourse.movie.domain

import java.time.LocalDate
import java.time.temporal.ChronoUnit

class MovieDateScheduler : DateScheduler {
    override fun reservableDates(
        screeningDate: ScreeningDate,
        currentDate: LocalDate,
    ): List<LocalDate> {
        val startDate = startDate(screeningDate.startDate, currentDate)
        return getDatesBetween(startDate, screeningDate.endDate)
    }

    override fun startDate(
        startDate: LocalDate,
        currentDate: LocalDate,
    ): LocalDate {
        return if (startDate < currentDate) {
            currentDate
        } else {
            startDate
        }
    }

    private fun getDatesBetween(
        startDate: LocalDate,
        endDate: LocalDate,
    ): List<LocalDate> {
        val dayDiff = ChronoUnit.DAYS.between(startDate, endDate)

        return (0..dayDiff).map { startDate.plusDays(it) }
    }
}
