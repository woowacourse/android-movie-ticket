package movie

import movie.screening.ScreeningDate
import movie.screening.ScreeningTime
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalTime

class MovieSchedule(
    private val movie: Movie,
    private val screeningDate: ScreeningDate,
) : Serializable {

    val poster: Int
        get() = movie.poster

    val title: String
        get() = movie.title

    val runningTime: Int
        get() = movie.runningTime

    val summary: String
        get() = movie.summary

    val startDate: LocalDate
        get() = screeningDate.startDate

    val endDate: LocalDate
        get() = screeningDate.endDate

    fun getScreeningTime(date: LocalDate): List<LocalTime> = ScreeningTime.getScreeningTime(date)

    fun getScreeningDate(): List<String> = screeningDate.getScreeningDate()
}
