package woowacourse.movie.domain.model.movie

data class Movie(
    val id: Long,
    val title: String,
    val poster: Poster,
    val screeningPeriod: ScreeningPeriod,
    val runningTime: RunningTime,
)
