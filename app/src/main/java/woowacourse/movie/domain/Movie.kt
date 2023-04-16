package woowacourse.movie.domain

data class Movie(
    val imagePath: String,
    val title: String,
    val date: DateRange,
    val runningTime: Int,
    val description: String,
)
