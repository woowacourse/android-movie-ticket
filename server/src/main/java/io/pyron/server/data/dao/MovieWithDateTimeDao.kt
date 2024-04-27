package io.pyron.server.data.dao

import io.pyron.server.data.db.DB_MOVIES
import io.pyron.server.data.db.DB_MOVIESCREENDATETIMES
import io.pyron.server.data.db.DB_SCREENDATETIMES
import io.pyron.server.data.dto.MovieWithScreenDateTime

class MovieWithDateTimeDao {
    fun findAll(): List<MovieWithScreenDateTime> {
        return DB_MOVIES.map { movie ->
            MovieWithScreenDateTime(
                id = movie.id,
                title = movie.title,
                description = movie.description,
                thumbnailUrl = movie.thumbnailUrl,
                screenDateTimes =
                    DB_MOVIESCREENDATETIMES
                        .filter { movie.id == it.movieId }
                        .flatMap { movieScreenDate ->
                            DB_SCREENDATETIMES
                                .filter { movieScreenDate.screenDateTimeId == it.id }
                        },
                runningTime = movie.runningTime,
            )
        }
    }

    fun findOneById(id: Long): MovieWithScreenDateTime? {
        return DB_MOVIES.firstOrNull { it.id == id }
            ?.let { movie ->
                MovieWithScreenDateTime(
                    id = movie.id,
                    title = movie.title,
                    description = movie.description,
                    thumbnailUrl = movie.thumbnailUrl,
                    screenDateTimes =
                        DB_MOVIESCREENDATETIMES
                            .filter { movie.id == it.movieId }
                            .flatMap { movieScreenDate ->
                                DB_SCREENDATETIMES
                                    .filter { movieScreenDate.screenDateTimeId == it.id }
                            },
                    runningTime = movie.runningTime,
                )
            }
    }
}
