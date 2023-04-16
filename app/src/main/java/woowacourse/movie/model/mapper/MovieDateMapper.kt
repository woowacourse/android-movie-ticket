package woowacourse.movie.model.mapper

import com.woowacourse.movie.domain.MovieDateDomain
import woowacourse.movie.model.MovieDate

fun MovieDateDomain.toPresentation(): MovieDate = MovieDate(year, month, day)

fun MovieDate.toDomain(): MovieDateDomain = MovieDateDomain(year, month, day)
