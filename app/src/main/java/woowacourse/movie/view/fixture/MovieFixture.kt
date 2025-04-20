package woowacourse.movie.view.fixture

import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.RunningTime
import woowacourse.movie.domain.model.ScreeningPeriod
import java.time.LocalDate

val dummyMovie =
    Movie(
        "해리 포터와 마법사의 돌",
        R.drawable.harrypotter.toString(),
        ScreeningPeriod(
            LocalDate.of(2025, 4, 1),
            LocalDate.of(2025, 4, 25),
        ),
        RunningTime(152),
    )
