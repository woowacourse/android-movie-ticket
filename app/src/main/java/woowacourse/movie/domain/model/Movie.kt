package woowacourse.movie.domain.model

data class Movie(
    val title: String,
    val startDate: MovieDate,
    val endDate: MovieDate,
    val runningTime: Int,
) {
    companion object {
        val movies: List<Movie> =
            listOf(
                Movie(
                    title = "해리 포터와 마법사의 돌",
                    startDate = MovieDate(2025, 4, 1),
                    endDate = MovieDate(2025, 4, 25),
                    runningTime = 152,
                ),
            )
    }
}
