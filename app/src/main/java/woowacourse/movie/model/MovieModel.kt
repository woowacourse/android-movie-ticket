package woowacourse.movie.model

import androidx.annotation.DrawableRes
import java.io.Serializable
import java.time.LocalDate

data class MovieModel(
    @DrawableRes
    val poster: Int,
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Int,
    val description: String,
) : Serializable
