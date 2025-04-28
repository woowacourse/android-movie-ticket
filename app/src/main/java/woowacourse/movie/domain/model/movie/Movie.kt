package woowacourse.movie.domain.model.movie

import java.io.Serializable

data class Movie(
    val title: String,
    val screeningDate: ScreeningDate,
    val runningTime: RunningTime,
    val imageUrl: Int,
) : Serializable
