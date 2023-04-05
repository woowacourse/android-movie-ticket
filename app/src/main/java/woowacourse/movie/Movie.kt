package woowacourse.movie

import java.io.Serializable
import java.time.LocalDate

data class Movie(
    val poster: Int,
    val title: String,
    val releaseDate: String,
    val runningTime: String,
    val synopsis: String,
    val startDate: LocalDate,
    val endDate: LocalDate
) : Serializable
