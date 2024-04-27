package io.pyron.server

import io.pyron.server.data.MovieRepositoryImpl
import io.pyron.server.data.MovieSeatRepositoryImpl
import io.pyron.server.data.ScreenDateTimeRepositoryImpl
import io.pyron.server.data.dto.MovieSeatResponse
import io.pyron.server.data.dto.MovieWithScreenDateTimeDTO
import io.pyron.server.data.dto.ScreenDateTimeResponse
import io.pyron.server.domain.MovieSeatRepository
import io.pyron.server.domain.MovieServerRepository
import io.pyron.server.domain.ScreenDateTimeRepository

// json 형태로 리턴해준다고 가정
class ServerApi(
    private val movieServerRepository: MovieServerRepository = MovieRepositoryImpl(),
    private val movieSeatRepository: MovieSeatRepository = MovieSeatRepositoryImpl(),
    private val screenDateTimeRepository: ScreenDateTimeRepository = ScreenDateTimeRepositoryImpl(),
) {
    fun findAllMovieWithDateTimes(): List<MovieWithScreenDateTimeDTO> {
        return movieServerRepository.findAllMovieWithDateTimes()
    }

    fun findOneMovieWithDateTime(id: Long): MovieWithScreenDateTimeDTO? {
        return movieServerRepository.findMovieWithDateTime(id)
    }

    fun findSeatsByScreenDateTime(movieScreenDateTimeId: Long): List<MovieSeatResponse> {
        return movieSeatRepository.findOneByMovieScreenDateTime(movieScreenDateTimeId).map {
            MovieSeatResponse.fromMovieSeat(it)
        }
    }

    fun findScreenDateTimeByMovieScreenDateTime(movieScreenDateTimeId: Long): ScreenDateTimeResponse? {
        return screenDateTimeRepository.findOneByMovieScreenDateTime(movieScreenDateTimeId)?.run {
            ScreenDateTimeResponse.from(this)
        }
    }

    fun findSeatById(seatId: Long): MovieSeatResponse? {
        return movieSeatRepository.findOneById(seatId)?.run {
            MovieSeatResponse.fromMovieSeat(this)
        }
    }
}
