package woowacourse.movie.domain.screening

data class Movie(
    val id: Long = 0,
    val poster: Int,
    val title: String,
    val content: String,
    val openingDay: String,
    val runningTime: Int,
)
