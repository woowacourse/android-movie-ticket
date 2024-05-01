package io.pyron.server.data

import io.pyron.server.data.dao.ScreenDateTimeDao
import io.pyron.server.data.entity.ScreenDateTime
import io.pyron.server.domain.ScreenDateTimeRepository

class ScreenDateTimeRepositoryImpl(
    private val screenDateTimeDao: ScreenDateTimeDao = ScreenDateTimeDao(),
) : ScreenDateTimeRepository {
    override fun findOneByMovieScreenDateTime(movieScreenDateTimeId: Long): ScreenDateTime? {
        return screenDateTimeDao.findOneByMovieScreenDateTimeId(movieScreenDateTimeId = movieScreenDateTimeId)
    }
}
