package woowacourse.movie

import androidx.annotation.DrawableRes

class Movie(
    val title: String,
    @DrawableRes val poster: Int,
    val releaseDate: String,
    val runningTime: String,
)
