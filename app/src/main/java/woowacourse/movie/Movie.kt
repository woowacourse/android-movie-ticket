package woowacourse.movie

import androidx.annotation.DrawableRes
import woowacourse.movie.entity.RunningTime
import java.io.Serializable
import java.time.LocalDate

data class Movie(
    @DrawableRes
    val imgResourceId: Int,
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: RunningTime,
    val description: String,
) : Serializable
