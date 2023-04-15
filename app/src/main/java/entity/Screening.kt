package entity

import movie.ScreeningDate
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalTime

class Screening(
    private val movie: Movie,
    private val screeningDate: ScreeningDate,
) : MovieListDto, Serializable {
    val startDate: LocalDate = screeningDate.startDate
    val endDate: LocalDate = screeningDate.endDate

    val poster: Int
        get() = movie.poster

    val title: String
        get() = movie.title

    val runningTime: Int
        get() = movie.runningTime

    val summary: String
        get() = movie.summary

    fun getScreeningTime(date: LocalDate): List<LocalTime> = screeningDate.getScreeningTime(date)

    fun getScreeningDate(): List<String> = screeningDate.dateList
}
