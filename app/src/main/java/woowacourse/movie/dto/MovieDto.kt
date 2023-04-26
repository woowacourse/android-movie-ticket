package woowacourse.movie.dto

import androidx.annotation.DrawableRes
import woowacourse.movie.movielist.ViewType
import java.io.Serializable
import java.time.LocalDate

data class MovieDto(
    val viewType: ViewType = ViewType.MOVIE_VIEW,
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Int,
    val description: String,
    @DrawableRes val moviePoster: Int,
) : Serializable
