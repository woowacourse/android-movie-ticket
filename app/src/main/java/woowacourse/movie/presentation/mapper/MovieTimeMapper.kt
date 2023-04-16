package woowacourse.movie.presentation.mapper

import woowacourse.movie.domain.model.movie.DomainMovieTime
import woowacourse.movie.presentation.model.MovieTime

fun MovieTime.toDomain(): DomainMovieTime =
    DomainMovieTime(hour, min)

fun DomainMovieTime.toPresentation(): MovieTime =
    MovieTime(hour, min)
