package woowacourse.movie.mapper

import woowacourse.movie.domain.movieinfo.MovieDate
import woowacourse.movie.dto.MovieDateDto

fun mapToMovieDate(movieDateDto: MovieDateDto): MovieDate {
    return MovieDate(movieDateDto.date)
}

fun mapToMovieDateDto(movieDate: MovieDate): MovieDateDto {
    return MovieDateDto(movieDate.date)
}
