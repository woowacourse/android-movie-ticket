package woowacourse.movie.mapper

import woowacourse.movie.domain.movieinfo.Movie
import woowacourse.movie.dto.MovieDto

fun mapToMovie(movieDto: MovieDto): Movie {
    return Movie(
        title = movieDto.title,
        startDate = movieDto.startDate,
        endDate = movieDto.endDate,
        runningTime = movieDto.runningTime,
        description = movieDto.description,
        moviePoster = movieDto.moviePoster,
    )
}

fun mapToMovieDto(movie: Movie): MovieDto {
    return MovieDto(
        title = movie.title,
        startDate = movie.startDate,
        endDate = movie.endDate,
        runningTime = movie.runningTime,
        description = movie.description,
        moviePoster = movie.moviePoster,
    )
}
