package woowacourse.movie.model

data class Movie(
    val posterImage: String,
    val title: String,
    val screeningDate: String,
    val runningTime: Int,
    val summary: String,
)
