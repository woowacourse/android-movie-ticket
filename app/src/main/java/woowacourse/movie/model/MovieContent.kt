package woowacourse.movie.model

data class MovieContent(
    val imageId: Int,
    val title: String,
    val screeningMovieDate: MovieDate,
    val runningTime: Int,
    val synopsis: String,
    val id: Long = 0,
)
