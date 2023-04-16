package woowacourse.movie.model.mapper

import com.woowacourse.movie.domain.MovieTimeDomain
import woowacourse.movie.model.MovieTime

fun MovieTimeDomain.toPresentation(): MovieTime = MovieTime(hour, min)

fun MovieTime.toDomain(): MovieTimeDomain = MovieTimeDomain(hour, min)
