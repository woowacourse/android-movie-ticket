package woowacourse.movie.model

import java.time.LocalDate

data class ScreeningMovie(
    val id: Long,
    val movie: Movie,
    val price: Price,
    val screenDateTimes: List<ScreenDateTime>,
) {
    companion object {
        val STUB: ScreeningMovie = ScreeningMovie(
            id = 1,
            movie = Movie.STUB,
            price = Price(13_000),
            screenDateTimes = listOf(
                ScreenDateTime(
                    date = LocalDate.of(2024, 3, 1),
                    times = emptyList()
                )
            )
        )
    }
}


