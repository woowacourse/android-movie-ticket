package woowacourse.movie

import kotlin.time.Duration
import kotlin.time.Duration.Companion.minutes

data class MovieReservation(
    val id: Long,
    val movie: ScreeningMovie,
    val headCount: HeadCount,
    val cancelDeadLine: Duration = 15.minutes
) {
    val totalPrice: Price get() = movie.price * headCount.count
}