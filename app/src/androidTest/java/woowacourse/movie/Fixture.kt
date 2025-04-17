package woowacourse.movie

import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.RunningTime
import woowacourse.movie.domain.ScreeningDate
import java.time.LocalDate
import java.time.format.DateTimeFormatter

val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
val APRIL_THIRTIETH: LocalDate = LocalDate.parse("2025.04.30", formatter)
val MAY_FOURTH: LocalDate = LocalDate.parse("2025.05.04", formatter)

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
const val HARRY_POTTER_FORMATTED_DATE = "2025.04.30 ~ 2025.05.04"
