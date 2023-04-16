package woowacourse.movie.uimodel

import androidx.annotation.DrawableRes
import java.io.Serializable
import java.time.LocalDate

class MovieScheduleUi(
    val title: String,
    val runningTime: Int,
    val summary: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    @DrawableRes val poster: Int,
) : Serializable
