package woowacourse.movie.model.mapper

import movie.domain.Count
import woowacourse.movie.model.CountState

fun CountState.toDomain(): Count = Count(value)

fun Count.toPresentation(): CountState = CountState(value)
