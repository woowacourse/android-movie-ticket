package woowacourse.movie.utils

import movie.data.Movie
import movie.data.MovieDetail
import movie.data.MovieSchedule
import movie.data.MovieTicket
import movie.screening.ScreeningDate
import movie.seat.Seat
import woowacourse.movie.uimodel.MovieDetailUi
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

fun MovieDetailUi.toDomain(): MovieDetail {
    return MovieDetail(count, title, date, time)
}

fun MovieTicketUi.toDomain(): MovieTicket {
    val movieTicket = MovieTicket(totalPrice, count, title, date, time)
    seats.forEach {
        movieTicket.addSeat(Seat.of(it))
    }
    return movieTicket
}
