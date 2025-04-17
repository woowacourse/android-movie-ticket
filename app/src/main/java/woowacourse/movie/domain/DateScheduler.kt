package woowacourse.movie.domain

import java.time.LocalDate
import java.time.temporal.ChronoUnit

class DateScheduler {
    fun reservableDates(
        screeningDate: ScreeningDate,
        currentDate: LocalDate,
    ): List<LocalDate> {
        val startDate = startDate(screeningDate.startDate, currentDate)
        return getDatesBetween(startDate, screeningDate.endDate)
    }

    fun startDate(
        movieStartDate: LocalDate,
        currentDate: LocalDate,
    ): LocalDate {
        return if (movieStartDate < currentDate) {
            currentDate
        } else {
            movieStartDate
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
