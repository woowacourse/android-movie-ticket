package woowacourse.movie.domain.model

import java.time.LocalDate

@JvmInline
value class MovieDate(
    val value: LocalDate = LocalDate.now(),
) {
    constructor(year: Int, month: Int, dayOfMonth: Int) : this(
        LocalDate.of(year, month, dayOfMonth),
    )
}
