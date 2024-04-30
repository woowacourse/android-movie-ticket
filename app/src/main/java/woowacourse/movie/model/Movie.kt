package woowacourse.movie.model

data class Movie(
    val id: Int,
    val title: String,
    val thumbnail: Int,
    val screeningDates: ScreeningDates,
    val runningTime: Int,
    val introduction: String,
)
