package io.pyron.server

import io.pyron.server.data.MovieServerRepositoryImpl
import io.pyron.server.data.dto.MovieWithDateTime
import io.pyron.server.data.entity.Movie
import io.pyron.server.domain.MovieServerRepository

class ServerApi(private val movieServerRepository: MovieServerRepository = MovieServerRepositoryImpl()) {

    // json 이라고 가정
    fun findAllMovieWithDateTimes(): List<MovieWithDateTime> {
        return movieServerRepository.findAllMovieWithDateTimes()
    }

    // json 이라고 가정
    fun findOneMovieWithDateTime(id: Long): MovieWithDateTime? {
        return movieServerRepository.findOneMovieWithDateTime(id)
    }

}