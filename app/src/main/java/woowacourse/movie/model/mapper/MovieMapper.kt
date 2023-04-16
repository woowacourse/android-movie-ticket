package woowacourse.movie.model.mapper

import com.woowacourse.movie.domain.MovieDomain
import woowacourse.movie.model.Movie

fun MovieDomain.toPresentation(): Movie = Movie(title, startDate, endDate, runningTime, introduce, thumbnail)

fun Movie.toDomain(): MovieDomain = MovieDomain(title, startDate, endDate, runningTime, introduce, thumbnail)
