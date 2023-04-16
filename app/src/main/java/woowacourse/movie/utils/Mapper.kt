package woowacourse.movie.utils

import movie.Movie
import movie.MovieSchedule
import movie.MovieTicket
import movie.screening.ScreeningDate
import woowacourse.movie.uimodel.MovieScheduleUi
import woowacourse.movie.uimodel.MovieTicketUi

fun MovieScheduleUi.toMovieSchedule(): MovieSchedule {
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

fun MovieTicketUi.toMovieTicket(): MovieTicket {
    return MovieTicket(eachPrice, count, title, date, time)
}
