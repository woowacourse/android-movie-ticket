package woowacourse.movie.mapper

import domain.movieinfo.MovieTime
import woowacourse.movie.dto.MovieTimeDto

fun MovieTimeDto.mapToMovieTime(): MovieTime {
    return MovieTime(this.time)
}

fun MovieTime.mapToMovieTimeDto(): MovieTimeDto {
    return MovieTimeDto(this.time)
}
