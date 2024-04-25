package woowacourse.movie.model.ticket

import java.time.LocalDate
import java.time.LocalTime

data class BookingDateTime(
    val date: LocalDate,
    val time: LocalTime,
)
