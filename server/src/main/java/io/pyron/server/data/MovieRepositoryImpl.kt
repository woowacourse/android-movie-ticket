package io.pyron.server.data

import io.pyron.server.data.dao.MovieDao
import io.pyron.server.data.dao.MovieWithScreenDateTimeDao
import io.pyron.server.data.dto.MovieWithScreenDateTime
import io.pyron.server.data.entity.Movie
import io.pyron.server.domain.MovieServerRepository

class MovieRepositoryImpl(
    private val movieDao: MovieDao = MovieDao(),
    private val movieWithScreenDateTimeDao: MovieWithScreenDateTimeDao = MovieWithScreenDateTimeDao(),
) : MovieServerRepository {
    override fun findAll(): List<Movie> {
        return movieDao.findAll()
    }

    override fun findOneById(id: Long): Movie? {
        return movieDao.findOneById(id)
    }

    override fun findAllMovieWithDateTimes(): List<MovieWithScreenDateTime> {
        return movieWithScreenDateTimeDao.findAll()
    }

    override fun findMovieWithDateTime(id: Long): MovieWithScreenDateTime? {
        return movieWithScreenDateTimeDao.findOneById(id)
    }
}
