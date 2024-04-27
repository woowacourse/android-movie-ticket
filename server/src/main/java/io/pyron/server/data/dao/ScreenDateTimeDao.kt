package io.pyron.server.data.dao

import io.pyron.server.data.db.DB_MOVIESCREENDATETIMES
import io.pyron.server.data.db.DB_SCREENDATETIMES
import io.pyron.server.data.entity.ScreenDateTime

class ScreenDateTimeDao {
    fun findOneByMovieScreenDateTime(movieScreenDateTimeId: Long): ScreenDateTime {
        val screenDateTimeId = DB_MOVIESCREENDATETIMES.first { it.id == movieScreenDateTimeId }.screenDateTimeId
        return DB_SCREENDATETIMES.first { it.id == screenDateTimeId }
    }
}
