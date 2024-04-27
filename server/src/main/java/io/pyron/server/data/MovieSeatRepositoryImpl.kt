package io.pyron.server.data

import io.pyron.server.data.dao.MovieSeatDao
import io.pyron.server.data.entity.MovieSeat
import io.pyron.server.domain.MovieSeatRepository

class MovieSeatRepositoryImpl(
    private val movieSeatDao: MovieSeatDao = MovieSeatDao(),
) : MovieSeatRepository {
    override fun findOneByMovieScreenDateTime(movieScreenDateTimeId: Long): List<MovieSeat> {
        return movieSeatDao.findAllByMovieScreenDateTime(movieScreenDateTimeId)
    }

    override fun findOneById(seatId: Long): MovieSeat? {
        return movieSeatDao.findOneById(seatId = seatId)
    }
}
