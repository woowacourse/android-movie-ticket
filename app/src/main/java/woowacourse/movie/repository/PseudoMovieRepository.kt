package woowacourse.movie.repository

import woowacourse.movie.model.movie.Movie
import woowacourse.movie.model.movie.MovieDetail
import woowacourse.movie.model.movie.RunningTime
import woowacourse.movie.model.movie.Synopsis
import woowacourse.movie.model.movie.Title
import woowacourse.movie.model.schedule.ScreeningDate
import woowacourse.movie.model.schedule.ScreeningPeriod

class PseudoMovieRepository : MovieRepository {
    override fun getMovies(): List<Movie> = movies

    override fun getMovie(movieId: Int): Movie = movies.getOrNull(movieId) ?: Movie.default

    companion object {
        private val pseudoMovieDetail =
            MovieDetail(
                Title("차람과 하디의 진지한 여행기"),
                RunningTime(230),
                Synopsis(
                    """
                    synopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsys
                    synopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsys
                    synopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsys
                    synopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsys
                    synopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsys
                    synopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsys
                    synopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsys
                    synopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsys
                    synopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsyssynopsys
                    """.trimIndent().replace("\n", "")
                ),
            )
        private val pseudoMovie = Movie(
            pseudoMovieDetail,
            ScreeningPeriod(
                ScreeningDate.of(2024, 4, 26),
                ScreeningDate.of(2024, 4, 28),
            )
        )

        private val movies = List(11) { pseudoMovie }
    }
}
