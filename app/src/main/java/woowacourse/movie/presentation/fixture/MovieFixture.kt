package woowacourse.movie.presentation.fixture

import woowacourse.movie.R
import woowacourse.movie.domain.model.movie.Movie
import woowacourse.movie.domain.model.movie.Poster
import woowacourse.movie.domain.model.movie.RunningTime
import woowacourse.movie.domain.model.movie.ScreeningPeriod
import java.time.LocalDate

val dummyMovie =
    Movie(
        1,
        "해리 포터와 마법사의 돌",
        Poster.Resource(R.drawable.harrypotter),
        ScreeningPeriod(
            LocalDate.of(2025, 4, 1),
            LocalDate.of(2025, 4, 25),
        ),
        RunningTime(152),
    )
