package io.pyron.server.domain

import io.pyron.server.data.dto.MovieWithScreenDateTime
import io.pyron.server.data.entity.Movie

interface MovieServerRepository {
    fun findAll(): List<Movie>

    fun findOneById(id: Long): Movie?

    fun findAllMovieWithDateTimes(): List<MovieWithScreenDateTime>

    fun findMovieWithDateTime(id: Long): MovieWithScreenDateTime?
}
