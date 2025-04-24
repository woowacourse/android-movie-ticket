package woowacourse.movie.model.movie.screening

import woowacourse.movie.model.movie.Movie
import java.time.LocalDate
import java.time.LocalTime

class Screening(
    movie: Movie,
    val period: ClosedRange<LocalDate>,
    private val showTimePolicy: ShowtimePolicy = DefaultShowtimePolicy(),
) {
    val movieId: String = movie.movieId
    val title: String = movie.title
    val runningTime: Int = movie.runningTime

    fun showtimes(date: LocalDate): List<LocalTime> = showTimePolicy.showtimes(date)

    fun getScreeningDates(): MutableList<LocalDate> {
        var currentDate = period.start
        val screeningDates = mutableListOf<LocalDate>()
        while (!currentDate.isAfter(period.endInclusive)) {
            screeningDates.add(currentDate)
            currentDate = currentDate.plusDays(1)
        }
        return screeningDates
    }

    companion object {
        const val HARRY_POTTER_1_MOVIE_ID = "HarryPotter1"

        fun getDefaultScreenings(): List<Screening> {
            val harryPotter =
                Movie(
                    "해리 포터와 마법사의 돌",
                    152,
                    HARRY_POTTER_1_MOVIE_ID,
                )
            val harryPotterScreening =
                Screening(
                    harryPotter,
                    LocalDate.of(2025, 4, 1)..LocalDate.of(2025, 4, 25),
                )
            return listOf(harryPotterScreening)
        }
    }
}
