package woowacourse.movie

import java.time.LocalDate
import java.time.LocalTime

data class ScreenDateTime(
    val date: LocalDate,
    val dateTime: List<LocalTime>
)