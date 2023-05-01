package woowacourse.movie.domain

import java.time.LocalDateTime

class Reservation(
    val screeningDateTime: LocalDateTime,
    val theater: Theater,
    val seatPoints: List<Point>
) {
}
