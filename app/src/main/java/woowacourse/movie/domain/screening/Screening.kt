package woowacourse.movie.domain.screening

import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class Screening(
    private val movie: Movie,
    private val start: LocalDate,
    private val end: LocalDate,
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Screening

        if (movie != other.movie) return false
        if (start != other.start) return false
        if (end != other.end) return false

        return true
    }

    override fun hashCode(): Int {
        var result = movie.hashCode()
        result = 31 * result + start.hashCode()
        result = 31 * result + end.hashCode()
        return result
    }

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
