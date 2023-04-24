package woowacourse.movie.view.model

import androidx.annotation.DrawableRes
import java.io.Serializable
import java.time.LocalDate
import java.time.Period

data class MovieUiModel(
    @DrawableRes val picture: Int,
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Int,
    val description: String,
) : UiModel, Serializable {
    fun getDateList(): List<LocalDate> {
        return (0..Period.between(startDate, endDate).days).map { startDate.plusDays(it.toLong()) }
    }
}
