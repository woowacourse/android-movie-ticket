package woowacourse.movie.domain.screening

import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

class Screening(
    movie: Movie,
    start: LocalDate,
    end: LocalDate,
    private val current: LocalDateTime = LocalDateTime.now(),
) : Serializable {
    val id: Int = movie.id
    val title: String = movie.title
    val runningTime: Int = movie.runningTime

    val startYear: Int = start.year
    val startMonth: Int = start.monthValue
    val startDay: Int = start.dayOfMonth

    val endYear: Int = end.year
    val endMonth: Int = end.monthValue
    val endDay: Int = end.dayOfMonth

    fun availableDates(now: LocalDate? = current.toLocalDate()): List<LocalDate> = dates.filterNot { date -> date.isBefore(now) }

    fun showtimes(
        date: LocalDate,
        showTimePolicy: ShowtimePolicy = DefaultShowtimePolicy(),
    ): List<LocalTime> = showTimePolicy.showtimes(date, current)

    private val dates: List<LocalDate> =
        run {
            var currentDate = start
            val screeningDates = mutableListOf<LocalDate>()
            while (!currentDate.isAfter(end)) {
                screeningDates.add(currentDate)
                currentDate = currentDate.plusDays(1)
            }
            screeningDates
        }
}
