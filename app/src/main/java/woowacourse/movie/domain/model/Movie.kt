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
                Movie(
                    title = "해리 포터와 비밀의 방",
                    startDate = MovieDate(2025, 4, 1),
                    endDate = MovieDate(2025, 4, 28),
                    runningTime = 162,
                ),
                Movie(
                    title = "해리 포터와 아즈카반의 죄수",
                    startDate = MovieDate(2025, 5, 1),
                    endDate = MovieDate(2025, 5, 31),
                    runningTime = 141,
                ),
                Movie(
                    title = "해리 포터와 불의 잔",
                    startDate = MovieDate(2025, 6, 1),
                    endDate = MovieDate(2025, 6, 30),
                    runningTime = 157,
                ),
            )
    }
}
