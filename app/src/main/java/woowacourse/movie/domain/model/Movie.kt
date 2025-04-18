package woowacourse.movie.domain.model

import java.io.Serializable

data class Movie(
    val title: String,
    val poster: String,
    val screeningPeriod: ScreeningPeriod,
    val runningTime: Int,
) : Serializable
