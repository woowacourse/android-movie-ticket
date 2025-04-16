package woowacourse.movie

import androidx.annotation.DrawableRes

data class Movie(
    val title: String,
    val runningTime: Int,
    @DrawableRes
    val posterId: Int,
)
