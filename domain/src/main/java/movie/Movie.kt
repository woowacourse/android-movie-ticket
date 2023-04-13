package movie

import java.io.Serializable

data class Movie(
    val title: String,
    val runningTime: Int,
    val summary: String,
    val poster: Int,
) : Serializable
