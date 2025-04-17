package woowacourse.movie

import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Reservation
import woowacourse.movie.domain.RunningTime
import woowacourse.movie.domain.ScreeningDate
import woowacourse.movie.domain.TicketCount
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
val APRIL_THIRTIETH: LocalDate = LocalDate.parse("2025.04.30", formatter)
val MAY_FOURTH: LocalDate = LocalDate.parse("2025.05.04", formatter)
val ON_TIME: LocalTime = LocalTime.of(12, 0)

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
        _count = TicketCount(2),
        reservedTime =
            LocalDateTime.of(
                APRIL_THIRTIETH,
                ON_TIME,
            ),
    )
