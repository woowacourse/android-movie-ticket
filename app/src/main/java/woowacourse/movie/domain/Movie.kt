package woowacourse.movie.domain

data class Movie(
    val poster: Image,
    val title: String,
    val date: DateRange,
    val runningTime: Int,
    val description: String,
)
