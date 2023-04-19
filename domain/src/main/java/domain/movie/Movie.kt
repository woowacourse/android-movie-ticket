package domain.movie

data class Movie(
    val movieName: MovieName,
    val screeningPeriod: ScreeningPeriod,
    val runningTime: RunningTime,
    val description: String
)
