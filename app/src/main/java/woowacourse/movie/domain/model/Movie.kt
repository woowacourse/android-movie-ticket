package woowacourse.movie.domain.model

data class Movie(
    val movieId: Int,
    val posterImageId: Int,
    val title: String,
    val screeningInfo: ScreeningInfo,
    val summary: String,
)
