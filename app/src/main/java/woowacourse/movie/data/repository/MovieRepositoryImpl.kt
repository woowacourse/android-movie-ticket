package woowacourse.movie.data.repository

import woowacourse.movie.data.SampleMovies
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.repository.MovieRepository

object MovieRepositoryImpl : MovieRepository {
    private val movies: List<Movie> = SampleMovies.movies
    private const val ERROR_INVALID_MOVIE_ID = "해당하는 영화가 없습니다."

    override fun getAllMovies(): List<Movie> {
        return movies
    }

    override fun getMovie(id: Int): Movie {
        return movies.find { movie: Movie ->
            movie.movieId == id
        } ?: throw IllegalArgumentException(ERROR_INVALID_MOVIE_ID)
    }
}
