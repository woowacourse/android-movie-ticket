package woowacourse.movie.domain.movie

data class Movie(
    val name: Name,
    val screeningPeriod: ScreeningPeriod,
    val runningTime: Int,
    val description: String
)
