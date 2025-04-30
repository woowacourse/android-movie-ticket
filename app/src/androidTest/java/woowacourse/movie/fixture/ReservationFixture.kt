package woowacourse.movie.fixture

import woowacourse.movie.domain.Reservation
import woowacourse.movie.domain.Seat
import woowacourse.movie.domain.Seats
import woowacourse.movie.domain.TicketCount
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

val APRIL_THIRTIETH: LocalDate = LocalDate.of(2025, 4, 30)

fun createReservation(
    movieName: String,
    seats: Seats = SEATS,
): Reservation =
    Reservation(
        movie = createMovie(movieName),
        _count = TicketCount(3),
        reservedTime =
            LocalDateTime.of(
                APRIL_THIRTIETH,
                LocalTime.of(12, 0),
            ),
        seats = seats,
    )

val SEATS =
    Seats(
        setOf(
            Seat(0, 0),
            Seat(2, 0),
            Seat(4, 0),
        ),
    )

const val SCREENING_DATE: String = "2025.04.30 12:00"
const val TICKET_COUNT: String = "일반 3명 | A1, C1, E1"
const val TOTAL_COUNT: String = "37,000원"
