package woowacourse.movie.data

import woowacourse.movie.domain.Movie

interface MovieRepository {
    fun fetchMovies(): List<Movie>
}

class DefaultMovieRepository : MovieRepository {
    override fun fetchMovies(): List<Movie> = DummyData.movies.map { it.key }
}
