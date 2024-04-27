package io.pyron.server

import io.pyron.server.data.MovieRepositoryImpl
import io.pyron.server.data.MovieSeatRepositoryImpl
import io.pyron.server.data.ScreenDateTimeRepositoryImpl
import io.pyron.server.data.dto.MovieWithScreenDateTime
import io.pyron.server.data.entity.MovieSeat
import io.pyron.server.data.entity.ScreenDateTime
import io.pyron.server.domain.MovieSeatRepository
import io.pyron.server.domain.MovieServerRepository
import io.pyron.server.domain.ScreenDateTimeRepository

// json 형태로 리턴해준다고 가정
class ServerApi(
    private val movieServerRepository: MovieServerRepository = MovieRepositoryImpl(),
    private val movieSeatRepository: MovieSeatRepository = MovieSeatRepositoryImpl(),
    private val screenDateTimeRepository: ScreenDateTimeRepository = ScreenDateTimeRepositoryImpl(),
) {
    fun findAllMovieWithDateTimes(): List<MovieWithScreenDateTime> {
        return movieServerRepository.findAllMovieWithDateTimes()
    }

    fun findOneMovieWithDateTime(id: Long): MovieWithScreenDateTime? {
        return movieServerRepository.findMovieWithDateTime(id)
    }

    fun findSeatsByScreenDateTime(movieScreenDateTimeId: Long): List<MovieSeat> {
        return movieSeatRepository.findOneByMovieScreenDateTime(movieScreenDateTimeId)
    }

    fun findScreenDateTimeByMovieScreenDateTime(movieScreenDateTimeId: Long): ScreenDateTime? {
        return screenDateTimeRepository.findOneByMovieScreenDateTime(movieScreenDateTimeId)
    }

    fun findSeatById(seatId: Long): MovieSeat? {
        return movieSeatRepository.findOneById(seatId)
    }
}
