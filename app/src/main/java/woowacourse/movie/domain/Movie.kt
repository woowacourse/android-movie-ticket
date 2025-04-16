package woowacourse.movie.domain

import androidx.annotation.DrawableRes

data class Movie(
    val title: String,
    val runningTime: Int,
    @DrawableRes
    val posterId: Int,
)
