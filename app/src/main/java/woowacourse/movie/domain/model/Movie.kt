package woowacourse.movie.domain.model

data class Movie(
    val movieId: Int,
    val title: String,
    val imageName: String?,
    val screeningDate: String,
    val runningTime: Int,
    val description: String,
) {
    companion object {
        const val DEFAULT_MOVIE_PRICE = 13_000
    }
}
