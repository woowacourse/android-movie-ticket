package woowacourse.movie.model.movieSelect.screening

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
    val capacityOfTheater: Int = DEFAULT_THEATER_CAPACITY

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
        private const val DEFAULT_THEATER_CAPACITY = 20

        fun getDefaultScreenings(): List<Screening> =
            List(10000) {
                Screening(
                    Movie(
                        "해리 포터 ${it + 1}",
                        (100..200).random(),
                        HARRY_POTTER_1_MOVIE_ID,
                    ),
                    LocalDate.of(2025, 4, (1..10).random())..LocalDate.of(
                        2025,
                        4,
                        (20..28).random(),
                    ),
                )
            }
    }
}
