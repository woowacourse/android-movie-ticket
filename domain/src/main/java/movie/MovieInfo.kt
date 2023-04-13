package movie

import java.io.Serializable
import java.time.LocalDate
import java.time.LocalTime

class MovieInfo(
    val movie: Movie,
    private val screeningDate: ScreeningDate,
) : Serializable {
    private val discountPolicy: DiscountPolicy = DiscountPolicy

    val startDate: LocalDate = screeningDate.startDate
    val endDate: LocalDate = screeningDate.endDate
    val poster: Int = movie.poster
    val title: String = movie.title
    val runningTime: Int = movie.runningTime
    val summary: String = movie.summary

    fun getDiscountPolicy(localDate: LocalDate, localTime: LocalTime): (Int) -> Int {
        return discountPolicy.of(localDate, localTime)
    }

    fun getScreeningTime(date: LocalDate): List<LocalTime> = screeningDate.getScreeningTime(date)

    fun getScreeningDate(): List<String> = screeningDate.dateList
}
