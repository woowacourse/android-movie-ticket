package woowacourse.movie.model

import androidx.annotation.DrawableRes

data class Movie(
    val id: Long,
    @DrawableRes
    val thumbnail: Int,
    val title: String,
    val description: String,
    val date: MovieDate,
    val runningTime: Int,
)
