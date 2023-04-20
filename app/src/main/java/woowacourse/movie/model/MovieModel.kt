package woowacourse.movie.model

import androidx.annotation.DrawableRes

data class MovieModel(
    @DrawableRes val image: Int,
    val title: String,
    val startDate: String,
    val endDate: String,
    val runningTime: Int,
    val description: String
) : java.io.Serializable
