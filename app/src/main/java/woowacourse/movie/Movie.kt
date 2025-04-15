package woowacourse.movie

import androidx.annotation.DrawableRes
import java.time.LocalDate

data class Movie(
    val title: String,
    private val screeningDate: LocalDate,
    val runningTime: Int,
    @DrawableRes
    val posterId: Int,
) {
    val screeningYear: Int = screeningDate.year
    val screeningMonth: Int = screeningDate.monthValue
    val screeningDay: Int = screeningDate.dayOfMonth
}
