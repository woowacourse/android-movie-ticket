package woowacourse.movie

import androidx.annotation.DrawableRes
import java.time.LocalDate

data class Movie(
    val title: String,
    val screeningDate: LocalDate,
    val runningTime: Int,
    @DrawableRes
    val posterId: Int,
)
