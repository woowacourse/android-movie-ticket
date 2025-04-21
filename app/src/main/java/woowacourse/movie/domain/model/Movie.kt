package woowacourse.movie.domain.model

import java.io.Serializable

data class Movie(
    val poster: String,
    val title: String,
    val screeningPeriod: ScreeningPeriod,
    val runningTime: RunningTime,
) : Serializable
