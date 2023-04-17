package woowacourse.movie.domain.movie

import androidx.annotation.DrawableRes
import java.time.LocalDate
import java.time.LocalTime

data class MovieDTO(
    @DrawableRes val image: Int,
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val playingTimes: Map<LocalDate, List<LocalTime>>,
    val runningTime: Int,
    val description: String
) : java.io.Serializable
