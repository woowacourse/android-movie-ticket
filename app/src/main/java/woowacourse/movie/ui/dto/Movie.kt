package woowacourse.movie.ui.dto

import androidx.annotation.DrawableRes
import java.io.Serializable
import java.time.LocalDate

data class Movie(
    @DrawableRes
    val poster: Int,
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Int,
    val description: String,
) : Serializable
