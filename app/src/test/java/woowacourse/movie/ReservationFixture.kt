package woowacourse.movie

import woowacourse.movie.domain.Point
import woowacourse.movie.domain.Points
import woowacourse.movie.domain.Reservation
import woowacourse.movie.domain.TicketCount
import java.time.LocalDateTime
import java.time.LocalTime

val B_CLASS: Point = Point(0, 0)
val A_CLASS: Point = Point(2, 0)
val S_CLASS: Point = Point(4, 0)

val RESERVATION_WITH_POINTS =
    Reservation(
        movie = HARRY_POTTER_MOVIE,
        _count = TicketCount(1),
        reservedTime =
            LocalDateTime.of(
                APRIL_THIRTIETH,
                LocalTime.of(12, 0),
            ),
        points =
            Points(
                setOf(
                    B_CLASS,
                    A_CLASS,
                    S_CLASS,
                ),
            ),
    )

val RESERVATION =
    Reservation(
        movie = HARRY_POTTER_MOVIE,
        _count = TicketCount(1),
        reservedTime =
            LocalDateTime.of(
                APRIL_THIRTIETH,
                LocalTime.of(12, 0),
            ),
    )
