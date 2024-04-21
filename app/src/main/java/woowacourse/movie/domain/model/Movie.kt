package woowacourse.movie.domain.model

data class Movie(
    val title: String,
    val posterResourceId: Int,
    val screeningDate: String,
    val runningTime: Int,
    val description: String,
) {
    companion object {
        const val KEY_NAME_MOVIE = "movie"
    }
}
