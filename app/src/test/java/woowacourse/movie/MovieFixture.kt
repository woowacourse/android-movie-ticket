package woowacourse.movie

import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.RunningTime
import woowacourse.movie.domain.model.ScreeningDate

const val HARRY_POTTER_TITLE = "해리포터"
const val HARRY_POTTER_RUNNING_TIME = 152
val HARRY_POTTER_MOVIE =
    Movie(
        title = HARRY_POTTER_TITLE,
        screeningDate =
            ScreeningDate(
                MAY_FIRST,
                MAY_SECOND,
            ),
        runningTime =
            RunningTime(
                HARRY_POTTER_RUNNING_TIME,
            ),
        imageUrl = R.drawable.harrypotter,
    )
