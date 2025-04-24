package woowacourse.movie.domain

import woowacourse.movie.domain.fixture.MoviesFixture
import woowacourse.movie.domain.model.movies.Movie
import woowacourse.movie.domain.model.movies.Movies
import woowacourse.movie.view.movies.MovieListContract

val fakeMoviesModel: MovieListContract.MovieModel =
    object : MovieListContract.MovieModel {
        private val movies = Movies(MoviesFixture)

        override fun getAll(): Movies = movies

        override fun get(index: Int): Movie = movies[index]

        override fun size(): Int = movies.size()
    }
