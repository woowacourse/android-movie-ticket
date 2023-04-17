package woowacourse.movie.utils

import movie.Movie
import movie.MovieSchedule
import movie.MovieTicket
import movie.screening.ScreeningDate
import woowacourse.movie.uimodel.MovieScheduleUi
import woowacourse.movie.uimodel.MovieTicketUi

fun MovieScheduleUi.toDomain(): MovieSchedule {
    return MovieSchedule(
        Movie(
            title,
            runningTime,
            summary,
        ),
        ScreeningDate(
            startDate,
            endDate,
        ),
    )
}

fun MovieTicketUi.toDomain(): MovieTicket {
    return MovieTicket(eachPrice, count, title, date, time)
}
