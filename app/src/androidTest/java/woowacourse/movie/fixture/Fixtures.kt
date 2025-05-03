package woowacourse.movie.fixture

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Ticket
import woowacourse.movie.domain.seat.Seat
import woowacourse.movie.domain.seat.Seats
import java.time.LocalDate
import java.time.LocalDateTime

val FAKE_CONTEXT: Context = ApplicationProvider.getApplicationContext()

val HARRY_POTTER_01 =
    Movie(
        title = "해리 포터와 마법사의 돌",
        startDate = LocalDate.of(2025, 4, 1),
        endDate = LocalDate.of(2025, 5, 30),
        runningTime = 152,
    )

val B3 = Seat.from(1, 2)
val D2 = Seat.from(3, 1)

val INITIAL_TICKET =
    Ticket(
        movie = HARRY_POTTER_01,
        showtime = LocalDateTime.of(2025, 4, 15, 11, 0, 0),
        count = 2,
        seats = Seats(setOf()),
    )

val TICKET =
    Ticket(
        movie = HARRY_POTTER_01,
        showtime = LocalDateTime.of(2025, 4, 15, 11, 0, 0),
        count = 2,
        seats = Seats(setOf(B3, D2)),
    )
