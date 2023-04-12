package woowacourse.movie

import androidx.annotation.DrawableRes
import java.io.Serializable
import java.time.LocalDate

data class Movie(
    val title: String,
    val releaseStartDate: LocalDate,
    val releaseEndDate: LocalDate,
    val runningTime: Int,
    val summary: String,
    @DrawableRes
    val poster: Int,
) : Serializable
