package woowacourse.movie.fixture

import woowacourse.movie.domain.Reservation
import woowacourse.movie.domain.TicketCount
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

val APRIL_THIRTIETH: LocalDate = LocalDate.of(2025, 4, 30)

fun createReservation(movieName: String): Reservation =
    Reservation(
        title = movieName,
        _count = TicketCount(2),
        reservedTime =
            LocalDateTime.of(
                APRIL_THIRTIETH,
                LocalTime.of(12, 0),
            ),
    )
