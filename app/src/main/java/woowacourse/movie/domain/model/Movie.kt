package woowacourse.movie.domain.model

data class Movie(
    val id: Long,
    val title: String,
    val poster: String,
    val screeningPeriod: ScreeningPeriod,
    val runningTime: RunningTime,
)
