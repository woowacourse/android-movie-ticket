package domain

import java.time.LocalDate

data class Movie(
    val name: String,
    val posterImage: Int?,
    val screeningDate: LocalDate,
    val runningTime: Int,
    val description: String
)
