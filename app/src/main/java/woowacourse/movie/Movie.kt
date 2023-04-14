package woowacourse.movie

import androidx.annotation.DrawableRes
import java.io.Serializable
import java.time.LocalDate

data class Movie(
    @DrawableRes val poster: Int,
    val title: String,
    val releaseDate: String,
    val runningTime: String,
    val synopsis: String,
    val startDate: LocalDate,
    val endDate: LocalDate
) : Serializable
