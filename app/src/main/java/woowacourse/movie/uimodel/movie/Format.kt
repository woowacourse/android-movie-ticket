package woowacourse.movie.uimodel.movie

import woowacourse.movie.model.movie.RunningTime
import woowacourse.movie.model.movie.Synopsis
import woowacourse.movie.model.movie.Title
import woowacourse.movie.model.schedule.ScreeningDate
import woowacourse.movie.model.schedule.ScreeningPeriod

internal fun Title.format() = content

internal fun ScreeningPeriod.format() = start.format() + "~" + end.format()

internal fun ScreeningDate.format() = "${date.year}.${date.monthValue}.${date.dayOfMonth}"

internal fun RunningTime.format() = time.toString()

internal fun Synopsis.format() = content
