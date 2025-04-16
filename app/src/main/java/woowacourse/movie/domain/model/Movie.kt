package woowacourse.movie.domain.model

data class Movie(
    val posterResId: Int,
    val title: String,
    val screeningPeriod: ScreeningPeriod,
    val runningTime: String,
)
