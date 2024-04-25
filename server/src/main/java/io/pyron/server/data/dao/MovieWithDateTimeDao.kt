package io.pyron.server.data.dao

import io.pyron.server.data.db.DB_MOVIES
import io.pyron.server.data.db.DB_MOVIESCREENDATETIMES
import io.pyron.server.data.db.DB_SCREENDATETIMES
import io.pyron.server.data.dto.MovieWithDateTime

class MovieWithDateTimeDao {
    fun findAll(): List<MovieWithDateTime> {
        return DB_MOVIES.map { movie ->
            MovieWithDateTime(
                id = movie.id,
                title = movie.title,
                description = movie.description,
                thumbnailUrl = movie.thumbnailUrl,
                dateTime = DB_MOVIESCREENDATETIMES
                    .filter { movie.id == it.movieId }
                    .flatMap { movieScreenDate ->
                        DB_SCREENDATETIMES
                            .filter { movieScreenDate.screenDateTimeId == it.id }
                            .map { it.dateTime }
                    },
                runningTime = movie.runningTime
            )
        }
    }

    fun findOneById(id: Long): MovieWithDateTime? {
        return DB_MOVIES.firstOrNull { it.id == id }
            ?.let {movie ->
                MovieWithDateTime(
                    id = movie.id,
                    title = movie.title,
                    description = movie.description,
                    thumbnailUrl = movie.thumbnailUrl,
                    dateTime = DB_MOVIESCREENDATETIMES
                        .filter { movie.id == it.movieId }
                        .flatMap { movieScreenDate ->
                            DB_SCREENDATETIMES
                                .filter { movieScreenDate.screenDateTimeId == it.id }
                                .map { it.dateTime }
                        },
                    runningTime = movie.runningTime
                )
            }
    }
}