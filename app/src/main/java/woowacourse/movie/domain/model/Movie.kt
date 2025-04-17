package woowacourse.movie.domain.model

import java.io.Serializable

data class Movie(
    val posterResId: Int,
    val title: String,
    val screeningPeriod: ScreeningPeriod,
    val runningTime: Int,
) : Serializable
