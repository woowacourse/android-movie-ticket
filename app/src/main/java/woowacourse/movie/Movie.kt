package woowacourse.movie

import androidx.annotation.DrawableRes
import java.time.LocalDate

data class Movie(
    val title: String,
    private val _screeningDate: LocalDate,
    val runningTime: Int,
    @DrawableRes
    val posterId: Int,
) {
    val screeningYear: Int = _screeningDate.year
    val screeningMonth: Int = _screeningDate.monthValue
    val screeningDay: Int = _screeningDate.dayOfMonth
}
