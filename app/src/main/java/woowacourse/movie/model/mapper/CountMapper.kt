package woowacourse.movie.model.mapper

import com.example.domain.model.Count
import woowacourse.movie.model.CountState

fun CountState.asDomain(): Count = Count(value)

fun Count.asPresentation(): CountState = CountState.from(this)
