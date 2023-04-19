package woowacourse.movie.utils

import movie.data.Movie
import movie.data.MovieDetail
import movie.data.MovieSchedule
import movie.screening.ScreeningDate
import woowacourse.movie.uimodel.MovieDetailUi
import woowacourse.movie.uimodel.MovieScheduleUi

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
