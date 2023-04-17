package woowacourse.movie.domain.model

import woowacourse.movie.domain.datetime.ScreeningPeriod

data class Movie(
    val title: String,
    val screeningDay: ScreeningPeriod,
    val runningTime: Int,
    val description: String = ""
)
