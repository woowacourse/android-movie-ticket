package woowacourse.movie.model

import androidx.annotation.DrawableRes
import java.io.Serializable

data class Movie(
    @DrawableRes val poster: Int,
    val title: String,
    val content: String,
    val openingDay: String,
    val runningTime: Int,
) : Serializable
