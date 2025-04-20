package woowacourse.movie.domain.model

import java.io.Serializable

data class Movie(
    val posterResId: String,
    val title: String,
    val screeningPeriod: ScreeningPeriod,
    val runningTime: Int,
) : Serializable
