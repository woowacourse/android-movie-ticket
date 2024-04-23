package woowacourse.movie.data.repository

import woowacourse.movie.data.SampleMovies
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.repository.MovieRepository

object MovieRepositoryImpl : MovieRepository {
    private val movies: List<Movie> = SampleMovies.movies
    
    override fun getAllMovies(): List<Movie> {
        return movies
    }

    override fun getMovie(id: Int): Movie {
        return movies.find { movie: Movie ->
            movie.movieId == id
        } ?: SampleMovies.defaultMovie
    }
}
