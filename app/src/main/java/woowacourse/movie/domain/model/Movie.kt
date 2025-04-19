package woowacourse.movie.domain.model

import androidx.annotation.DrawableRes
import java.io.Serializable

data class Movie(
    val title: String,
    @DrawableRes val poster: Int,
    val releaseDate: ScreeningDate,
    val runningTime: String,
) : Serializable
