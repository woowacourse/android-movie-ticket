package woowacourse.movie.domain

import java.io.Serializable
import java.time.LocalDate

data class Movie(
    val picture: Int,
    val title: String,
    val date: LocalDate,
    val runningTime: Int,
    val description: String,
) : Serializable
