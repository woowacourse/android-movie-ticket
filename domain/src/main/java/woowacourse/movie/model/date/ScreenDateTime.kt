package woowacourse.movie.model.date

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

data class ScreenDateTime(
    val date: LocalDate,
    val times: List<LocalTime>,
)
