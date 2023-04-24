package woowacourse.movie.utils

import movie.data.MovieDetail
import movie.data.MovieSchedule
import movie.screening.ScreeningDate
import movie.seat.Seat
import woowacourse.movie.uimodel.MovieDetailUi
import woowacourse.movie.uimodel.MovieModelUi
import woowacourse.movie.uimodel.SeatUi

fun MovieModelUi.MovieScheduleUi.toDomain(): MovieSchedule {
    return MovieSchedule(
        ScreeningDate(
            startDate,
            endDate,
        ),
    )
}

fun MovieDetailUi.toDomain(): MovieDetail {
    return MovieDetail(count, title, date, time)
}

fun Seat.toUi(): SeatUi {
    return SeatUi(row, col)
}
