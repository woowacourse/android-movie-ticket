package woowacourse.movie.domain

data class Movie(
    val image: String,
    val title: String,
    val playingDate: String,
    val runningTime: Int,
    val description: String
)
