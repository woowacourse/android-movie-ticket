package io.pyron.server.domain

import io.pyron.server.data.entity.ScreenDateTime

interface ScreenDateTimeRepository {
    fun findOneByMovieScreenDateTime(movieScreenDateTimeId: Long): ScreenDateTime?
}
