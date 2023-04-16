package woowacourse.movie.mapper

import woowacourse.movie.domain.movieinfo.MovieTime
import woowacourse.movie.dto.MovieTimeDto

fun mapToMovieTime(movieTimeDto: MovieTimeDto): MovieTime {
    return MovieTime(movieTimeDto.time)
}

fun mapToMovieTimeDto(movieTime: MovieTime): MovieTimeDto {
    return MovieTimeDto(movieTime.time)
}
