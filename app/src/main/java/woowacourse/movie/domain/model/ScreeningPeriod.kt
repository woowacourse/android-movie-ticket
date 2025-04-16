package woowacourse.movie.domain.model

import java.time.LocalDateTime

data class ScreeningPeriod(
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
)
