package woowacourse.movie.presentation.mapper

import woowacourse.movie.domain.model.movie.DomainMovie
import woowacourse.movie.presentation.model.Movie

fun Movie.toDomain(): DomainMovie =
    DomainMovie(title, startDate, endDate, runningTime, introduce, thumbnail)

fun DomainMovie.toPresentation(): Movie =
    Movie(title, startDate, endDate, runningTime, introduce, thumbnail)
