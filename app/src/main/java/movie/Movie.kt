package movie

import java.util.Date

data class Movie(
    val name: String,
    val screeningDate: Date,
    val runningTime: Int,
    val description: String
)
