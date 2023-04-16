package woowacourse.movie.dto

import woowacourse.movie.domain.DateRange
import woowacourse.movie.domain.Movie

class MovieDtoConverter : DtoConverter<Movie, MovieDto> {
    override fun convertDtoToModel(movieDto: MovieDto): Movie {
        return Movie(
            picture = movieDto.picture,
            title = movieDto.title,
            date = DateRange(movieDto.startDate, movieDto.endDate),
            runningTime = movieDto.runningTime,
            description = movieDto.description
        )
    }

    override fun convertModelToDto(movie: Movie): MovieDto {
        return MovieDto(
            picture = movie.picture,
            title = movie.title,
            startDate = movie.date.startDate,
            endDate = movie.date.endDate,
            runningTime = movie.runningTime,
            description = movie.description
        )
    }
}
