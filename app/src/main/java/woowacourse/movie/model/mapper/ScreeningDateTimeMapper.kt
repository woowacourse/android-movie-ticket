package woowacourse.movie.model.mapper

import movie.domain.datetime.ScreeningDateTime
import woowacourse.movie.model.ScreeningDateTimeState

fun ScreeningDateTimeState.toDomain(): ScreeningDateTime = ScreeningDateTime(dateTime, screeningPeriod.toDomain())

fun ScreeningDateTime.toPresentation(): ScreeningDateTimeState = ScreeningDateTimeState(dateTime, screeningPeriod.toPresentation())
