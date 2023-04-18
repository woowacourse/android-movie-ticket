package movie.domain

import movie.domain.datetime.ScreeningPeriod

data class MovieData(
    val posterImage: Int,
    val title: String,
    val screeningDay: ScreeningPeriod,
    val runningTime: Int,
    val description: String = ""
)
