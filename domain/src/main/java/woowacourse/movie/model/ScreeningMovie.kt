package woowacourse.movie.model

import java.time.LocalDate

sealed interface ScreenView

data class Advertisement(
    val imageUrl: ImageUrl = ImageUrl.none(),
) : ScreenView

data class ScreeningMovie(
    val id: Long,
    val movie: Movie,
    val theater: MovieTheater,
    val screenDateTimes: List<ScreenDateTime>,
) : ScreenView {
    val startDate: LocalDate = screenDateTimes.first().date

    val endDate: LocalDate = screenDateTimes.last().date

    fun screeningTimeOfDate(screeningDate: LocalDate): ScreenDateTime =
        screenDateTimes.firstOrNull { it.date.isEqual(screeningDate) }
            ?: error("해당 날짜에는 상영하지 않습니다.")

    companion object {
        val STUB: ScreeningMovie =
            ScreeningMovie(
                id = 1,
                movie = Movie.STUB,
                theater =
                    MovieTheater(
                        mapOf(
                            SeatRate.B to listOf(1, 2),
                            SeatRate.S to listOf(3, 4),
                            SeatRate.A to listOf(5),
                        ),
                        4,
                    ),
                screenDateTimes =
                    ScreeningDateTimeSystem().generate(
                        LocalDate.of(
                            2024,
                            3,
                            1,
                        ),
                        LocalDate.of(2024, 3, 31),
                    ),
            )
    }
}
