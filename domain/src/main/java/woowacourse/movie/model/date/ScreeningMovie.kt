package woowacourse.movie.model.date

import woowacourse.movie.model.Movie
import woowacourse.movie.model.Price
import woowacourse.movie.model.RunningTime
import java.time.LocalDate
import kotlin.time.Duration.Companion.minutes

data class ScreeningMovie(
    val id: Long,
    val movie: Movie,
    val price: Price,
    val screenDateTimes: ScreenDateTimes = ScreenDateTimes(emptyList()),
) {
    companion object {
        private val STUB: ScreeningMovie =
            ScreeningMovie(
                id = -1,
                movie = Movie.STUB,
                price = Price(10000),
                screenDateTimes =
                    DefaultScreenDateTimesGenerator.generate(
                        (1..30).map {
                            LocalDate.of(2024, 4, it)
                        },
                    ),
            )
        private val Stubs: List<ScreeningMovie> =
            listOf(
                STUB.copy(),
                STUB.copy(movie = Movie.STUB.copy(title = "해리 포터와 비밀의 방")),
                STUB.copy(
                    movie =
                        Movie.STUB.copy(
                            title = "해리 포터와 아즈카반의 죄수",
                            runningTime = RunningTime(162.minutes),
                        ),
                ),
                STUB.copy(
                    movie =
                        Movie.STUB.copy(
                            title = "해리 포터와 불사조의 기사단",
                            runningTime = RunningTime(172.minutes),
                        ),
                ),
            )

        fun stubMovies(): List<ScreeningMovie> {
            val movies = List(2500) { Stubs }.flatten()
            return movies.indices.map {
                movies[it].copy(id = it.toLong())
            }
        }
    }
}
