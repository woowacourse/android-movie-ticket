package woowacourse.movie.model.mapper

import movie.domain.MovieData
import woowacourse.movie.model.MovieDataState

fun MovieDataState.toDomain(): MovieData =
    MovieData(posterImage, title, screeningDay.toDomain(), runningTime, description)

fun MovieData.toPresentation(): MovieDataState =
    MovieDataState(posterImage, title, screeningDay.toPresentation(), runningTime, description)
