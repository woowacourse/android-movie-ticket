package woowacourse.movie

import woowacourse.movie.domain.Reservation
import woowacourse.movie.domain.Seat
import woowacourse.movie.domain.Seats
import woowacourse.movie.domain.TicketCount
import java.time.LocalDateTime
import java.time.LocalTime

val B_CLASS: Seat = Seat(0, 0)
val A_CLASS: Seat = Seat(2, 0)
val S_CLASS: Seat = Seat(4, 0)

val RESERVATION_WITH_POINTS =
    Reservation(
        movie = HARRY_POTTER_MOVIE,
        _count = TicketCount(1),
        reservedTime =
            LocalDateTime.of(
                APRIL_THIRTIETH,
                LocalTime.of(12, 0),
            ),
        seats =
            Seats(
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
