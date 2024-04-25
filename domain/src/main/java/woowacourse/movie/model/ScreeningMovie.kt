package woowacourse.movie.model

import java.time.LocalDate
import java.time.LocalTime

data class ScreeningMovie(
    val id: Long,
    val movie: Movie,
    val price: Price,
    val screenDateTimes: List<ScreenDateTime>,
) {
    companion object {
        val STUB: ScreeningMovie =
            ScreeningMovie(
                id = 1,
                movie = Movie.STUB,
                price = Price(13_000),
                screenDateTimes =
                    listOf(
                        ScreenDateTime(
                            date = LocalDate.of(2024, 3, 1),
                            times =
                                listOf(
                                    LocalTime.of(9, 0),
                                    LocalTime.of(10, 0),
                                    LocalTime.of(11, 0),
                                    LocalTime.of(12, 0),
                                    LocalTime.of(13, 0),
                                ),
                        ),
                        ScreenDateTime(
                            date = LocalDate.of(2024, 3, 3),
                            times =
                                listOf(
                                    LocalTime.of(9, 0),
                                    LocalTime.of(10, 0),
                                    LocalTime.of(11, 0),
                                    LocalTime.of(12, 0),
                                    LocalTime.of(13, 0),
                                ),
                        ),
                        ScreenDateTime(
                            date = LocalDate.of(2024, 4, 1),
                            times =
                                listOf(
                                    LocalTime.of(9, 0),
                                    LocalTime.of(10, 0),
                                    LocalTime.of(11, 0),
                                    LocalTime.of(12, 0),
                                    LocalTime.of(13, 0),
                                ),
                        ),
                    ),
            )
    }
}
