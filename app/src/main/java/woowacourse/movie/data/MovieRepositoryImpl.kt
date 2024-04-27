package woowacourse.movie.data

import woowacourse.movie.data.remote.FakeRemoteDataSource
import woowacourse.movie.data.remote.RemoteDataSource
import woowacourse.movie.data.remote.mapper.MovieMapper
import woowacourse.movie.data.remote.mapper.MovieSeatMapper
import woowacourse.movie.data.remote.mapper.ScreenDateTimeMapper
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.MovieRepository
import woowacourse.movie.domain.MovieSeat
import woowacourse.movie.domain.ScreenDateTime

class MovieRepositoryImpl(private val remoteDataSource: RemoteDataSource = FakeRemoteDataSource()) : MovieRepository {
    override fun findAllMovies(): List<Movie> {
        return remoteDataSource.findAllMovies().map {
            MovieMapper.fromMovieResponse(it)
        }
    }

    override fun findMovieById(movieId: Long): Movie? {
        return remoteDataSource.findMovieById(movieId)?.let {
            MovieMapper.fromMovieResponse(it)
        }
    }

    override fun findSeatsByMovieScreenDateTimeId(movieScreenDateTimeId: Long): List<MovieSeat> {
        return remoteDataSource.findAllSeatsById(movieScreenDateTimeId).map {
            MovieSeatMapper.fromMovieSeatResponse(it)
        }
    }

    override fun findSeatById(seatId: Long): MovieSeat? {
        return remoteDataSource.findSeatById(seatId)?.let {
            MovieSeatMapper.fromMovieSeatResponse(it)
        }
    }

    override fun findScreenDateTimeByMovieScreenDateTimeId(movieScreenDateTimeId: Long): ScreenDateTime? {
        return remoteDataSource.findScreenDateTimeByMovieScreenDateTimeId(movieScreenDateTimeId)?.let {
            ScreenDateTimeMapper.fromScreenDateTimeResponse(it)
        }
    }
}
