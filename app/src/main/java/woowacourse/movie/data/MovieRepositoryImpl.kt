package woowacourse.movie.data

import woowacourse.movie.data.remote.FakeRemoteDataSource
import woowacourse.movie.data.remote.RemoteDataSource
import woowacourse.movie.data.remote.mapper.MovieMapper
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.MovieRepository

class MovieRepositoryImpl(private val remoteDataSource: RemoteDataSource = FakeRemoteDataSource()) : MovieRepository {
    override fun findAll(): List<Movie> {
        return remoteDataSource.findAll().map {
            MovieMapper.fromMovieResponse(it)
        }
    }

    override fun findOneById(id: Long): Movie? {
        return remoteDataSource.findOneById(id)?.let {
            MovieMapper.fromMovieResponse(it)
        }
    }
}
