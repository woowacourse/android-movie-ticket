package domain.movie

data class Movie(
    val movieName: MovieName,
    val screeningPeriod: ScreeningPeriod,
    val runningTime: Int,
    val description: String
)
