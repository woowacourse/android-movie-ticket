package woowacourse.movie.mapper

import woowacourse.movie.domain.movieinfo.MovieDate
import woowacourse.movie.dto.MovieDateDto

fun MovieDateDto.mapToMovieDate(): MovieDate {
    return MovieDate(this.date)
}

fun MovieDate.mapToMovieDateDto(): MovieDateDto {
    return MovieDateDto(this.date)
}
