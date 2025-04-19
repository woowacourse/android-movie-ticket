package woowacourse.movie.model.data

import java.io.Serializable
import java.time.LocalDate

data class Movie(
    val title: String,
    val startScreeningDate: LocalDate,
    val endScreeningDate: LocalDate,
    val runningTime: Int,
    val posterKey: String,
) : Serializable
