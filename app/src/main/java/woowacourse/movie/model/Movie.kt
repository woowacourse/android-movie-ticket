package woowacourse.movie.model

import androidx.annotation.DrawableRes

data class Movie(
    val id: Long,
    val title: String,
    @DrawableRes
    val thumbnailResourceId: Int,
    val date: String,
    val runningTime: Int,
    val introduction: String,
)
