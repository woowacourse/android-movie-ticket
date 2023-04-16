package woowacourse.movie.domain

data class Movie(
    val picture: Int,
    val title: String,
    val date: DateRange,
    val runningTime: Int,
    val description: String,
) {
    companion object {
        const val MOVIE_KEY_VALUE = "movie"
    }
}
