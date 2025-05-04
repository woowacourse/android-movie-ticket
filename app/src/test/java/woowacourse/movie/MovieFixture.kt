package woowacourse.movie

import woowacourse.movie.domain.model.reservation.Reservation
import woowacourse.movie.domain.model.ticket.Tickets
import woowacourse.movie.ui.model.MovieUiModel
import java.time.LocalDateTime

const val HARRY_POTTER_TITLE = "해리포터"
const val HARRY_POTTER_RUNNING_TIME = 152
val HARRY_POTTER_MOVIE =
    MovieUiModel(
        title = HARRY_POTTER_TITLE,
        startDate =
        MAY_FIRST,
        endDate = MAY_SECOND,
        runningMinute = HARRY_POTTER_RUNNING_TIME,
        poster = R.drawable.harrypotter,
    )

val HARRY_POTTER_RESERVATION =
    Reservation(
        title = HARRY_POTTER_TITLE,
        tickets =
            Tickets(
                listOf(),
            ),
        reservedTime =
            LocalDateTime.of(
                APRIL_THIRTIETH,
                ON_TIME,
            ),
    )
