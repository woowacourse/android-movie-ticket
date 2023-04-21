package woowacourse.movie

import woowacourse.movie.movie.MovieDatabase
import woowacourse.movie.movie.MovieEntity

object MovieRepository {
    fun getMovies(): List<Movie> {
        return MovieDatabase.movies.map { it.toMovie() }
    }

    fun getMovie(movieId: Long): Movie {
        return MovieDatabase.selectMovie(movieId).toMovie()
    }

    private fun MovieEntity.toMovie(): Movie {
        return Movie(
            id = this.id,
            title = this.title,
            startDate = this.startDate,
            endDate = this.endDate,
            runningTime = this.runningTime,
            description = this.description,
        )
    }
}
