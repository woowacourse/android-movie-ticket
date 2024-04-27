package io.pyron.server.domain

import io.pyron.server.data.entity.MovieSeat

interface MovieSeatRepository {
    fun findOneByMovieScreenDateTime(movieScreenDateTimeId: Long): List<MovieSeat>

    fun findOneById(id: Long): MovieSeat?
}
