package woowacourse.movie.model

data class MovieReservation(
    val movie: ScreeningMovie,
    val headCount: HeadCount,
    val cancelDeadLine: Int
)