package woowacourse.movie.domain

data class Movie(
    val title: String,
    val date: String,
    val runningTime: RunningTime,
    val imageUrl: Int,
)
