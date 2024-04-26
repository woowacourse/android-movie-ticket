package woowacourse.movie.model.screening

import androidx.annotation.DrawableRes

data class Movie(
    val movieId: Long,
    val title: String,
    @DrawableRes
    val thumbnailResourceId: Int,
    val runningTime: Int,
    val introduction: String,
)
