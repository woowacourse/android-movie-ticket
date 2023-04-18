package woowacourse.movie.dto

import androidx.annotation.DrawableRes
import java.io.Serializable
import java.time.LocalDate

data class MovieDto(
    @DrawableRes val picture: Int,
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Int,
    val description: String,
) : Dto, Serializable
