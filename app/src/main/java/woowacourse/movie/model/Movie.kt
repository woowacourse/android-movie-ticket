package woowacourse.movie.model

import androidx.annotation.DrawableRes
import java.time.LocalDate

data class Movie(
    @DrawableRes val imageSource: Int,
    val title: String,
    val screeningDate: LocalDate,
    val runningTime: Int,
)
