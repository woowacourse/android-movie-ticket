package woowacourse.movie.utils

import movie.data.Movie
import movie.data.MovieSchedule
import movie.data.MovieTicket
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
