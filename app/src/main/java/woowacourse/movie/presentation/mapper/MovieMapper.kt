package woowacourse.movie.presentation.mapper

import androidx.annotation.DrawableRes
import woowacourse.movie.domain.model.movie.DomainMovie
import woowacourse.movie.presentation.model.movieitem.Movie

fun Movie.toDomain(): DomainMovie =
    DomainMovie(title, startDate, endDate, runningTime, introduce)

fun DomainMovie.toPresentation(@DrawableRes thumbnailResId: Int): Movie =
    Movie(title, startDate, endDate, runningTime, introduce, thumbnailResId)
