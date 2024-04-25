package woowacourse.movie.data.remote

import woowacourse.movie.data.remote.dto.MovieResponse

interface RemoteDataSource {
    fun findAll(): List<MovieResponse>

    fun findOneById(id: Long): MovieResponse?
}
