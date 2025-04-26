package woowacourse.movie.fixture

import woowacourse.movie.domain.Point
import woowacourse.movie.domain.Points
import woowacourse.movie.domain.Reservation
import woowacourse.movie.domain.TicketCount
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

val APRIL_THIRTIETH: LocalDate = LocalDate.of(2025, 4, 30)

fun createReservation(movieName: String): Reservation =
    Reservation(
        movie = createMovie(movieName),
        _count = TicketCount(3),
        reservedTime =
            LocalDateTime.of(
                APRIL_THIRTIETH,
                LocalTime.of(12, 0),
            ),
        points =
            Points(
                setOf(
                    Point(0, 0),
                    Point(2, 0),
                    Point(4, 0),
                ),
            ),
    )

const val SCREENING_DATE: String = "2025.04.30 12:00"
const val TICKET_COUNT: String = "일반 3명 | A1, C1, E1"
const val TOTAL_COUNT: String = "37,000원"
