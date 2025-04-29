package woowacourse.movie.fixtures

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import woowacourse.movie.domain.movie.Movie
import woowacourse.movie.domain.movie.Ticket
import woowacourse.movie.domain.movie.TicketCount
import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.domain.seat.Seats
import woowacourse.movie.feature.model.toDomain
import woowacourse.movie.feature.model.toUiModel
import java.time.LocalDate
import java.time.LocalDateTime

val fakeContext: Context = ApplicationProvider.getApplicationContext()

val movie =
    Movie(
        title = "해리 포터와 마법사의 돌",
        startDate = LocalDate.of(2025, 4, 1),
        endDate = LocalDate.of(2025, 4, 25),
        runningTime = 152,
    ).toUiModel()

val ticket =
    Ticket(
        movie = movie.toDomain(),
        showtime = LocalDateTime.of(2025, 4, 15, 11, 0, 0),
        count = TicketCount.of(3),
    ).toUiModel()

val seats =
    Seats(3).apply {
        add(Seat(0, 0))
        add(Seat(2, 0))
        add(Seat(4, 0))
    }.toUiModel()
