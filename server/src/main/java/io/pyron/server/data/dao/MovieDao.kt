package io.pyron.server.data.dao

import io.pyron.server.data.db.dbMovies
import io.pyron.server.data.entity.Movie

class MovieDao {
    fun findAll(): List<Movie> {
        return dbMovies
    }

    fun findOneById(id: Long): Movie? {
        return dbMovies.firstOrNull { it.id == id }
    }
}
