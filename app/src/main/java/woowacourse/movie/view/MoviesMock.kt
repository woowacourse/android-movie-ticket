package woowacourse.movie.view

import woowacourse.movie.R
import woowacourse.movie.domain.DateRange
import woowacourse.movie.domain.Movie
import woowacourse.movie.domain.Movies
import java.time.LocalDate

object MoviesMock {
    fun create(): Movies = Movies(
        listOf(
            Movie(
                R.drawable.poster_harrypotter,
                "해리 포터",
                DateRange(
                    LocalDate.of(2024, 3, 1),
                    LocalDate.of(2024, 3, 31),
                ),
                153,
                "adsfasdfadsf",
            )
        )
    )
}
