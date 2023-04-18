package woowacourse.movie.domain

import woowacourse.movie.R
import java.time.LocalDate

object MovieMock {
    fun create(): Movie = Movie(
        Image(R.drawable.poster_harrypotter),
        "해리 포터",
        DateRange(
            LocalDate.of(2024, 3, 1),
            LocalDate.of(2024, 3, 31),
        ),
        153,
        "adsfasdfadsf",
    )
}
