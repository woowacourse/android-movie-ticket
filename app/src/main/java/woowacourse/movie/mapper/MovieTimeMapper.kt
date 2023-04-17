package woowacourse.movie.mapper

import woowacourse.movie.domain.movieinfo.MovieTime
import woowacourse.movie.dto.MovieTimeDto

fun MovieTimeDto.mapToMovieTime(): MovieTime {
    return MovieTime(this.time)
}

fun MovieTime.mapToMovieTimeDto(): MovieTimeDto {
    return MovieTimeDto(this.time)
}
