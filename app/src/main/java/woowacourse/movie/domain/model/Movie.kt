package woowacourse.movie.domain.model

data class Movie(
    val title: String,
    val poster: String,
    val screeningPeriod: ScreeningPeriod,
    val runningTime: RunningTime,
)
