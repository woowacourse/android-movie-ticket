package woowacourse.movie

import woowacourse.movie.domain.model.movie.Movie
import woowacourse.movie.domain.model.movie.RunningTime
import woowacourse.movie.domain.model.movie.ScreeningDate
import woowacourse.movie.domain.model.reservation.Reservation
import woowacourse.movie.domain.model.seat.Column
import woowacourse.movie.domain.model.seat.Row
import woowacourse.movie.domain.model.seat.Seat
import woowacourse.movie.domain.model.seat.SeatRate
import woowacourse.movie.domain.model.ticket.Ticket
import woowacourse.movie.domain.model.ticket.Tickets
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
