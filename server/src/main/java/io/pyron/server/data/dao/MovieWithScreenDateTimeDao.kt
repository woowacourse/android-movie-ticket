package io.pyron.server.data.dao

import io.pyron.server.data.db.dbMovieScreenDateTimes
import io.pyron.server.data.db.dbMovies
import io.pyron.server.data.db.dbScreenDateTimes
import io.pyron.server.data.dto.MovieWithScreenDateTimeDTO

// Movie - ScreenDateTime 조인
class MovieWithScreenDateTimeDao {
    fun findAll(): List<MovieWithScreenDateTimeDTO> {
        return dbMovies.map { movie ->
            MovieWithScreenDateTimeDTO(
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

    fun findOneById(id: Long): MovieWithScreenDateTimeDTO? {
        return dbMovies.firstOrNull { it.id == id }
            ?.let { movie ->
                MovieWithScreenDateTimeDTO(
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
