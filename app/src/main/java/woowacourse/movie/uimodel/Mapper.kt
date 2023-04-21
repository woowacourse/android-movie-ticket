package woowacourse.movie.utils

import movie.data.MovieDetail
import movie.data.MovieSchedule
import movie.screening.ScreeningDate
import woowacourse.movie.uimodel.MovieDetailUi
import woowacourse.movie.uimodel.MovieModelUi

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
