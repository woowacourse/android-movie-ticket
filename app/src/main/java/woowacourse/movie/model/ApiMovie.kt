package woowacourse.movie.model

data class ApiMovie(
    val poster: Int,
    val title: String,
    val content: String,
    val openingDay: String,
    val runningTime: Int,
    val price: Int = 13_000,
)
