package woowacourse.movie.model

import androidx.annotation.DrawableRes
import java.time.LocalDate

data class Movie(
    val id: Long,
    val title: String,
    @DrawableRes
    val thumbnailResourceId: Int,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Int,
    val introduction: String,
)
