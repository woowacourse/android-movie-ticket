package woowacourse.movie.dto

import androidx.annotation.DrawableRes
import java.io.Serializable
import java.time.LocalDate

data class MovieDto(
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Int,
    val description: String,
    @DrawableRes val moviePoster: Int,
) : Serializable
