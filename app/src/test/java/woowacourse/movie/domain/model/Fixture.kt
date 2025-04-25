package woowacourse.movie.domain.model

import woowacourse.movie.R
import woowacourse.movie.model.Movie
import java.time.LocalDate

object Fixture {
    val dummyMovie =
        Movie(
            "라라랜드",
            R.drawable.lalaland,
            LocalDate.of(2025, 4, 1),
            LocalDate.of(2025, 4, 30),
            120,
        )
}
