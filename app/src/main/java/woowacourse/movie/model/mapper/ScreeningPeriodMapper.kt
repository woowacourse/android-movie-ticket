package woowacourse.movie.model.mapper

import movie.domain.datetime.ScreeningPeriod
import woowacourse.movie.model.ScreeningPeriodState

fun ScreeningPeriodState.toDomain(): ScreeningPeriod = ScreeningPeriod(start, end)

fun ScreeningPeriod.toPresentation(): ScreeningPeriodState = ScreeningPeriodState(start, end)
