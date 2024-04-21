package woowacourse.movie.domain.model

data class Movie(
    val movieId: Int,
    val title: String,
    val posterResourceId: Int?,
    val screeningDate: String,
    val runningTime: Int,
    val description: String,
)
