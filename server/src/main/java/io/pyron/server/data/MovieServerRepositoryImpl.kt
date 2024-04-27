package io.pyron.server.data

import io.pyron.server.data.dao.MovieDao
import io.pyron.server.data.dao.MovieWithDateTimeDao
import io.pyron.server.data.dto.MovieWithScreenDateTime
import io.pyron.server.data.entity.Movie
import io.pyron.server.domain.MovieServerRepository

class MovieServerRepositoryImpl(
    private val movieDao: MovieDao = MovieDao(),
    private val movieWithDateTimeDao: MovieWithDateTimeDao = MovieWithDateTimeDao(),
) : MovieServerRepository {
    override fun findAll(): List<Movie> {
        return movieDao.findAll()
    }

    override fun findOneById(id: Long): Movie? {
        return movieDao.findOneById(id)
    }

    override fun findAllMovieWithDateTimes(): List<MovieWithScreenDateTime> {
        return movieWithDateTimeDao.findAll()
    }

    override fun findMovieWithDateTime(id: Long): MovieWithScreenDateTime? {
        return movieWithDateTimeDao.findOneById(id)
    }
}
