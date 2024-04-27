package io.pyron.server.data.dao

import io.pyron.server.data.db.dbMovieScreenDateTimes
import io.pyron.server.data.db.dbMovies
import io.pyron.server.data.db.dbScreenDateTimes
import io.pyron.server.data.dto.MovieWithScreenDateTime

// Movie - ScreenDateTime 조인
class MovieWithScreenDateTimeDao {
    fun findAll(): List<MovieWithScreenDateTime> {
        return dbMovies.map { movie ->
            MovieWithScreenDateTime(
                id = movie.id,
                title = movie.title,
                description = movie.description,
                thumbnailUrl = movie.thumbnailUrl,
                screenDateTimes =
                    dbMovieScreenDateTimes
                        .filter { movie.id == it.movieId }
                        .flatMap { movieScreenDate ->
                            dbScreenDateTimes
                                .filter { movieScreenDate.screenDateTimeId == it.id }
                        },
                runningTime = movie.runningTime,
            )
        }
    }

    fun findOneById(id: Long): MovieWithScreenDateTime? {
        return dbMovies.firstOrNull { it.id == id }
            ?.let { movie ->
                MovieWithScreenDateTime(
                    id = movie.id,
                    title = movie.title,
                    description = movie.description,
                    thumbnailUrl = movie.thumbnailUrl,
                    screenDateTimes =
                        dbMovieScreenDateTimes
                            .filter { movie.id == it.movieId }
                            .flatMap { movieScreenDate ->
                                dbScreenDateTimes
                                    .filter { movieScreenDate.screenDateTimeId == it.id }
                            },
                    runningTime = movie.runningTime,
                )
            }
    }
}
