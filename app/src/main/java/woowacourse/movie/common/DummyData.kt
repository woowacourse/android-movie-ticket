package woowacourse.movie.common

import woowacourse.movie.R
import woowacourse.movie.domain.Movie
import java.time.LocalDate

object DummyData {
    val movies: List<Movie> =
        List(100) {
            Movie(
                title = "해리 포터와 마법사의 돌 $it",
                startDate = LocalDate.of(2025, 4, 1),
                endDate = LocalDate.of(2025, 4, 25),
                runningTime = 152,
                poster = R.drawable.harry_potter_poster,
            )
        }
}
