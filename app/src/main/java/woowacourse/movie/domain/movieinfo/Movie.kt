package woowacourse.movie.domain.movieinfo

import androidx.annotation.DrawableRes
import java.io.Serializable

data class Movie(
    val title: String,
    val runningDate: RunningDate,
    val runningTime: Int,
    val description: String,
    @DrawableRes val moviePoster: Int,
) : Serializable
