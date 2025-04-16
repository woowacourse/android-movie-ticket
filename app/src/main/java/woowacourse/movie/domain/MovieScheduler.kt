package woowacourse.movie.domain

import java.time.LocalDate

class MovieScheduler {
    fun reservableDates(
        screeningDate: ScreeningDate,
        currentDate: LocalDate,
    ): List<LocalDate> {
        val startDate = getStartDate(screeningDate.startDate, currentDate)
        return getDatesBetween(startDate, screeningDate.endDate)
    }

    private fun getStartDate(
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
        val dates = mutableListOf<LocalDate>()
        var current = startDate

        while (!current.isAfter(endDate)) {
            dates.add(current)
            current = current.plusDays(1)
        }

        return dates
    }
}
