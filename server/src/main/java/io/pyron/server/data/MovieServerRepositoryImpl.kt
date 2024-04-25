package io.pyron.server.data

import io.pyron.server.data.dao.MovieDao
import io.pyron.server.data.dao.MovieWithDateTimeDao
import io.pyron.server.data.entity.Movie
import io.pyron.server.domain.MovieServerRepository
import io.pyron.server.data.dto.MovieWithDateTime

class MovieServerRepositoryImpl(
    private val movieDao: MovieDao = MovieDao(),
    private val movieWithDateTimeDao: MovieWithDateTimeDao = MovieWithDateTimeDao()
): MovieServerRepository {

    override fun findAllMovies(): List<Movie> {
        return movieDao.findAll()
    }

    override fun findOneMovieById(id: Long): Movie? {
        return movieDao.findOneById(id)
    }

    override fun findAllMovieWithDateTimes(): List<MovieWithDateTime> {
        return movieWithDateTimeDao.findAll()
    }

    override fun findOneMovieWithDateTime(id: Long): MovieWithDateTime? {
        return movieWithDateTimeDao.findOneById(id)
    }
}