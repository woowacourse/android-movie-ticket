package woowacourse.movie.fixtures

import woowacourse.movie.domain.movie.Movie
import woowacourse.movie.domain.movie.Ticket
import woowacourse.movie.domain.movie.TicketCount
import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.domain.seat.Seats
import woowacourse.movie.feature.model.toDomain
import woowacourse.movie.feature.model.toUiModel
import java.time.LocalDate
import java.time.LocalDateTime

val MOVIE =
    Movie(
        title = "해리 포터와 마법사의 돌",
        startDate = LocalDate.of(2025, 5, 1),
        endDate = LocalDate.of(2025, 5, 2),
        runningTime = 152,
    ).toUiModel()

val TICKET =
    Ticket(
        movie = MOVIE.toDomain(),
        showtime = LocalDateTime.of(2025, 5, 1, 18, 0, 0),
        count = TicketCount.of(3),
    ).toUiModel()

val SEATS_FULL =
    Seats(3).apply {
        add(Seat(0, 0))
        add(Seat(0, 1))
        add(Seat(0, 2))
    }.toUiModel()
