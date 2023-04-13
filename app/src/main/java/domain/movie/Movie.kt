package domain.movie

import java.io.Serializable

data class Movie(
    val name: Name,
    val posterImage: Int?,
    val screeningPeriod: ScreeningPeriod,
    val runningTime: Int,
    val description: String
) : Serializable
