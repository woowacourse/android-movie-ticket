package woowacourse.movie.model

import androidx.annotation.DrawableRes

data class Movie(
    val id: Int,
    @DrawableRes
    val posterId: Int,
    val title: String,
    val screeningDate: String,
    val runningTime: String,
    val summary: String,
)
