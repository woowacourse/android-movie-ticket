package woowacourse.movie.mapper

import domain.movieinfo.Movie
import woowacourse.movie.dto.MovieDto

fun MovieDto.mapToMovie(): Movie {
    return Movie(
        title = this.title,
        startDate = this.startDate,
        endDate = this.endDate,
        runningTime = this.runningTime,
        description = this.description,
        moviePoster = this.moviePoster,
    )
}

fun Movie.mapToMovieDto(): MovieDto {
    return MovieDto(
        title = this.title,
        startDate = this.startDate,
        endDate = this.endDate,
        runningTime = this.runningTime,
        description = this.description,
        moviePoster = this.moviePoster,
    )
}
