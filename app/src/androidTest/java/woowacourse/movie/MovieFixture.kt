package woowacourse.movie

import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.ScreeningPeriod
import woowacourse.movie.domain.Title
import java.time.LocalDate

object MovieFixture {
    const val HARRY_POTTER_TITLE = "해리포터와 마법사의 돌"
    const val HARRY_POTTER_DATE = "상영일: 2025.04.01 ~ 2025.04.30"
    const val HARRY_POTTER_RUNNING_TIME = "러닝타임: 152분"

    val MOVIE = Movie(
        Title("해리포터와 마법사의 돌"),
        R.drawable.movie_poster,
        ScreeningPeriod(
            LocalDate.of(2025, 4, 1),
            LocalDate.of(2025, 4, 30),
        ),
        152,
    )
}
