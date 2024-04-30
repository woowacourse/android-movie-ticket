package woowacourse.movie.model.screening

import java.time.LocalDate

data class DatePeriod(
    val startDate: LocalDate,
    val endDate: LocalDate,
    val dateSpan: Long,
)
