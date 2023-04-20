package woowacourse.movie.domain

import java.time.LocalDate

object MovieMock {
    fun create(): Movie = Movie(
        Image(0),
        "해리 포터",
        DateRange(
            LocalDate.of(2024, 3, 1),
            LocalDate.of(2024, 3, 31),
        ),
        153,
        "adsfasdfadsf",
    )
}
