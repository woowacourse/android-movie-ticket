package woowacourse.movie.domain.model

data class Movie(
    val posterImageId: Int,
    val title: String,
    val screeningDate: Date,
    val runningTime: Int,
    val summary: String,
)
