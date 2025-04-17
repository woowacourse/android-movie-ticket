package woowacourse.movie.domain

import androidx.annotation.DrawableRes

class Movie(
    val title: String,
    val runningTime: Int,
    @DrawableRes
    val posterId: Int,
)
