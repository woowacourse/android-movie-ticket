package movie.data

import java.time.LocalDate
import java.time.LocalTime

class MovieDetail(
    val count: TicketCount,
    val title: String,
    val date: LocalDate,
    val time: LocalTime,
) {
    fun isUpOfCount(value: Int): Boolean = count.isUp(value)
}
