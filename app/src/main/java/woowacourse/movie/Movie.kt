package woowacourse.movie

import androidx.annotation.DrawableRes

data class Movie(
    val title: String,
    val releaseDate: String,
    val runningTime: String,
    @DrawableRes
    val poster: Int,
)
