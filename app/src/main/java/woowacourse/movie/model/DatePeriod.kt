package woowacourse.movie.model

import java.time.LocalDate

data class DatePeriod(
    val startDate: LocalDate,
    val endDate: LocalDate,
    val dateSpan: Long,
)
