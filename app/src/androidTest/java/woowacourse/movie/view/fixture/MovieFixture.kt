package woowacourse.movie.view.fixture

import woowacourse.movie.R
import woowacourse.movie.domain.model.Movie
import woowacourse.movie.domain.model.ScreeningPeriod
import java.time.LocalDate

object MovieFixture {
    val harryPotter =
        Movie(
            title = "해리 포터와 마법사의 돌",
            screeningPeriod = ScreeningPeriod(LocalDate.of(2025, 4, 1), LocalDate.of(2025, 4, 25)),
            runningTime = 152,
            posterResId = R.drawable.harrypotter.toString(),
        )
}
