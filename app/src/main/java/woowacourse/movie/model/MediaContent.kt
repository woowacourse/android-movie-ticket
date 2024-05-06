package woowacourse.movie.model

import java.time.LocalDate

sealed interface MediaContent

data class Movie(
    val id: Int,
    val poster: Int,
    val title: String,
    val firstScreeningDate: LocalDate,
    val lastScreeningDate: LocalDate,
    val runningTime: String,
    val summary: String,
) : MediaContent

data class Advertisement(
    val id: Int,
    val poster: Int,
) : MediaContent
