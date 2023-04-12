package woowacourse.movie.domain

import java.io.Serializable
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.LocalTime

class Movie(
    val title: String,
    val screeningStartDate: LocalDate,
    val screeningEndDate: LocalDate,
    val runningTime: Minute,
    val poster: Poster,
    val movieDetail: MovieDetail
) : Serializable {

    fun getAllScreeningDates(): List<LocalDate> {
        val screeningDates = mutableListOf<LocalDate>()
        var screeningDate = screeningStartDate
        while (screeningDate <= screeningEndDate) {
            screeningDates.add(screeningDate)
            screeningDate = screeningDate.plusDays(1)
        }
        return screeningDates
    }

    fun getAllScreeningTimes(date: LocalDate): List<LocalTime> {
        val screeningTimes = mutableListOf<LocalTime>()
        val firstScreeningTime = getFirstScreeningTime(date)
        var screeningTime = getFirstScreeningTime(date)
        while (true) {
            screeningTimes.add(screeningTime)
            screeningTime = screeningTime.plusHours(SCREENING_TIME_INTERVAL)
            if (screeningTime < firstScreeningTime) break
        }
        if (date.isWeekend().not()) screeningTimes.add(LocalTime.MIDNIGHT)
        return screeningTimes
    }

    fun getFirstScreeningTime(date: LocalDate): LocalTime =
        if (date.isWeekend()) FIRST_SCREENING_TIME_IN_WEEKEND else FIRST_SCREENING_TIME_IN_WEEKDAY

    private fun LocalDate.isWeekend(): Boolean =
        dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY

    companion object {
        private val FIRST_SCREENING_TIME_IN_WEEKDAY = LocalTime.of(10, 0)
        private val FIRST_SCREENING_TIME_IN_WEEKEND = LocalTime.of(9, 0)
        private const val SCREENING_TIME_INTERVAL = 2L
    }
}
