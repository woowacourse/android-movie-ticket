package woowacourse.movie.presentation.mapper

import woowacourse.movie.domain.model.movie.DomainMovieDate
import woowacourse.movie.presentation.model.MovieDate

fun MovieDate.toDomain(): DomainMovieDate =
    DomainMovieDate(year, month, day)

fun DomainMovieDate.toPresentation(): MovieDate =
    MovieDate(year, month, day)
