package woowacourse.movie

import androidx.annotation.DrawableRes
import java.io.Serializable

data class Movie(
    val title: String,
    val releaseDate: String,
    val runningTime: String,
    val summary: String,
    @DrawableRes
    val poster: Int,
) : Serializable
