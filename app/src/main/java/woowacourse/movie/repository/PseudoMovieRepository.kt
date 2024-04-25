package woowacourse.movie.repository

import woowacourse.movie.model.movie.Movie
import woowacourse.movie.model.movie.MovieDetail
import woowacourse.movie.model.movie.RunningTime
import woowacourse.movie.model.movie.Synopsis
import woowacourse.movie.model.movie.Title
import woowacourse.movie.model.movie.ScreeningDate
import java.time.LocalDate

class PseudoMovieRepository : MovieRepository {
    override fun getMovies(): List<Movie> = movies

    override fun getMovie(screeningId: Int): Movie = movies.getOrNull(screeningId) ?: Movie.default

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
                    """.trimIndent(),
                ),
            )
        private val pseudoMovie =
            Movie(
                pseudoMovieDetail,
                ScreeningDate(LocalDate.of(2024, 2, 25)),
            )

        private val movies =
            listOf(
                pseudoMovie,
                pseudoMovie,
                pseudoMovie,
                pseudoMovie,
                pseudoMovie,
                pseudoMovie,
                pseudoMovie,
                pseudoMovie,
                pseudoMovie,
            )
    }
}
