package woowacourse.movie.model

import androidx.annotation.DrawableRes
import java.time.LocalDate
import java.time.LocalTime
import java.util.TreeMap

data class MovieDTO(
    @DrawableRes val image: Int,
    val title: String,
    val startDate: LocalDate,
    val endDate: LocalDate,
    val playingDateTimes: TreeMap<LocalDate, List<LocalTime>>,
    val runningTime: Int,
    val description: String
) : java.io.Serializable
