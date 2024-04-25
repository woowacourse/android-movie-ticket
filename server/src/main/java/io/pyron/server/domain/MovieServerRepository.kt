package io.pyron.server.domain

import io.pyron.server.data.entity.Movie
import io.pyron.server.data.dto.MovieWithDateTime

interface MovieServerRepository {

    fun findAllMovies(): List<Movie>

    fun findOneMovieById(id: Long): Movie?

    fun findAllMovieWithDateTimes(): List<MovieWithDateTime>

    fun findOneMovieWithDateTime(id: Long): MovieWithDateTime?

}