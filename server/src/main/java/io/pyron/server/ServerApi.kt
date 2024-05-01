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
        // 현재 DB에 영화 데이터가 관계도로 1개 저장되어 있습니다.
        // 10,000개의 데이터를 보여주는 리사이클러뷰 구현을 위해 이렇게 서버에서 동일한 데이터를 준다고 API에서 지정했습니다.
        val item = movieServerRepository.findAllMovieWithDateTimes().first()
        return List(2500) {
            item
        }
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
