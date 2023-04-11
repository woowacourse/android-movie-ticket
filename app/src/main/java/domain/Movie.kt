package domain

import java.time.LocalDate

data class Movie(
    val name: String,
    val screeningDate: LocalDate,
    val runningTime: Int,
    val description: String
)
