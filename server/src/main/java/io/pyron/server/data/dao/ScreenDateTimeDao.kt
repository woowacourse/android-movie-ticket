package io.pyron.server.data.dao

import io.pyron.server.data.db.dbMovieScreenDateTimes
import io.pyron.server.data.db.dbScreenDateTimes
import io.pyron.server.data.entity.ScreenDateTime

class ScreenDateTimeDao {
    fun findOneByMovieScreenDateTimeId(movieScreenDateTimeId: Long): ScreenDateTime? {
        val screenDateTimeId = dbMovieScreenDateTimes.firstOrNull { it.id == movieScreenDateTimeId }?.screenDateTimeId
        return dbScreenDateTimes.firstOrNull { it.id == screenDateTimeId }
    }
}
