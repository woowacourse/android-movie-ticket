package woowacourse.movie.data

import androidx.annotation.VisibleForTesting
import woowacourse.movie.repository.MovieRepository

object MovieRepositoryFactory {
    private var movieRepository: MovieRepository? = null

    fun movieRepository(): MovieRepository {
        return movieRepository ?: FakeMovieRepository
    }

    @VisibleForTesting
    fun setMovieRepository(repository: MovieRepository) {
        movieRepository = repository
    }

    @VisibleForTesting
    fun clear() {
        movieRepository = null
    }
}
