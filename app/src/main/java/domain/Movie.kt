package domain

import java.io.Serializable

data class Movie(
    val name: String,
    val posterImage: Int?,
    val screeningPeriod: ScreeningPeriod,
    val runningTime: Int,
    val description: String
) : Serializable
