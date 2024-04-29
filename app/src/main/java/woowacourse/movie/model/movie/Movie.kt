package woowacourse.movie.model.movie

import androidx.annotation.DrawableRes
import java.time.LocalDate

data class Movie(
    val id: Int,
    @DrawableRes
    val posterId: Int,
    val title: String,
    val screeningPeriod: List<LocalDate>,
    val screeningTimes: ScreeningTimes,
    val runningTime: String,
    val summary: String,
)
