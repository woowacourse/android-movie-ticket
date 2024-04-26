package woowacourse.movie.repository

import woowacourse.movie.model.movie.Movie
import woowacourse.movie.model.movie.MovieDetail
import woowacourse.movie.model.movie.RunningTime
import woowacourse.movie.model.movie.Synopsis
import woowacourse.movie.model.movie.Title

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
                    """.trimIndent().trimEnd(),
                ),
            )
        private val pseudoMovie = Movie.default

        private val movies = List(11) { pseudoMovie }
    }
}
