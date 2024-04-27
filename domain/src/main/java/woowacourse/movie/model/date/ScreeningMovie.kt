package woowacourse.movie.model.date

import woowacourse.movie.model.Movie
import woowacourse.movie.model.Price
import java.time.LocalDate


data class ScreeningMovie(
    val id: Long,
    val movie: Movie,
    val price: Price,
    val screenDateTimes: ScreenDateTimes = ScreenDateTimes(emptyList()),
) {
    companion object {
        val STUB: ScreeningMovie =
            ScreeningMovie(
                id = 1,
                movie = Movie.STUB,
                price = Price(13_000),
                screenDateTimes = DefaultScreenDateTimesGenerator.generate(
                    (1..30).map {
                        LocalDate.of(2024, 4, it)
                    }
                )
            )
    }
}
