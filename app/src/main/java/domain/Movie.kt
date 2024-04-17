package domain

data class Movie(
    val id: Int,
    val poster: Int,
    val title: String,
    val screeningDate: String,
    val runningTime: String,
    val summary: String,
)
