package woowacourse.movie.model.movie

data class MovieContent(
    val imageId: String,
    val title: String,
    val screeningMovieDate: MovieDate,
    val runningTime: Int,
    val synopsis: String,
    val id: Long = 0,
)
