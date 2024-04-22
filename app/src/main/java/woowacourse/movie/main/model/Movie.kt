package woowacourse.movie.main.model

import androidx.annotation.DrawableRes

data class Movie(
    val id: Long,
    @DrawableRes
    val thumbnail: Int,
    val title: String,
    val description: String,
    val date: Long,
    val runningTime: Int,
)
