package woowacourse.movie.view.model

import androidx.annotation.DrawableRes
import java.io.Serializable
import java.time.LocalDate

data class MovieViewModel(
    @DrawableRes val picture: Int,
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val runningTime: Int,
    val description: String,
) : ViewModel, Serializable
