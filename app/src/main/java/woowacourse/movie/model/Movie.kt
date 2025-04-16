package woowacourse.movie.model

import java.io.Serializable
import java.time.LocalDate

data class Movie(
    val title: String,
    val startScreeningDate: LocalDate,
    val endScreeningDate: LocalDate,
    val runningTime: Int,
    val posterRes: Int,
) : Serializable
