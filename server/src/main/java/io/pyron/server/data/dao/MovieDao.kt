package io.pyron.server.data.dao

import io.pyron.server.data.db.DB_MOVIES
import io.pyron.server.data.entity.Movie

class MovieDao() {
    fun findAll(): List<Movie> {
        return DB_MOVIES
    }

    fun findOneById(id: Long): Movie? {
        return DB_MOVIES.firstOrNull { it.id == id }
    }
}
