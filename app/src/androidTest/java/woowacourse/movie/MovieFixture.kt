package woowacourse.movie

import woowacourse.movie.domain.model.Column
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.Reservation
import woowacourse.movie.domain.model.Row
import woowacourse.movie.domain.model.RunningTime
import woowacourse.movie.domain.model.ScreeningDate
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.model.SeatRate
import woowacourse.movie.domain.model.Ticket
import woowacourse.movie.domain.model.Tickets
import java.time.LocalDateTime

const val HARRY_POTTER_TITLE = "해리포터"
const val HARRY_POTTER_RUNNING_TIME = 152
val HARRY_POTTER_MOVIE =
    Movie(
        title = HARRY_POTTER_TITLE,
        screeningDate =
            ScreeningDate(
                APRIL_THIRTIETH,
                MAY_FOURTH,
            ),
        runningTime =
            RunningTime(
                HARRY_POTTER_RUNNING_TIME,
            ),
        imageUrl = R.drawable.harrypotter,
    )
val HARRY_POTTER_RESERVATION =
    Reservation(
        title = HARRY_POTTER_TITLE,
        tickets =
            Tickets(
                listOf(
                    Ticket(Seat(Row(0), Column(0), SeatRate.S)),
                    Ticket(Seat(Row(0), Column(1), SeatRate.B)),
                ),
            ),
        reservedTime =
            LocalDateTime.of(
                APRIL_THIRTIETH,
                ON_TIME,
            ),
    )
