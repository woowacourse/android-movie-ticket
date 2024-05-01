package woowacourse.movie.data.remote

import woowacourse.movie.data.remote.dto.MovieResponse
import woowacourse.movie.data.remote.dto.MovieSeatResponse
import woowacourse.movie.data.remote.dto.ScreenDateTimeResponse

interface RemoteDataSource {
    fun findAllMovies(): List<MovieResponse>

    fun findMovieById(id: Long): MovieResponse?

    fun findAllSeatsById(movieScreenDateTimeId: Long): List<MovieSeatResponse>

    fun findSeatById(seatId: Long): MovieSeatResponse?

    fun findScreenDateTimeByMovieScreenDateTimeId(movieScreenDateTimeId: Long): ScreenDateTimeResponse?
}
