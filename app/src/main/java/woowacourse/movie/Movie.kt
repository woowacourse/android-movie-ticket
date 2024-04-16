package woowacourse.movie

data class Movie(
    val poster: Int,
    val title: String,
    val openingDay: String,
    val runningTime: Int,
    val price: Int = 13_000,
)
