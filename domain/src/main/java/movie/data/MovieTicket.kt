package movie.data

import java.time.LocalDate
import java.time.LocalTime

data class MovieTicket(
    val eachPrice: Int,
    val count: TicketCount,
    val title: String,
    val date: LocalDate,
    val time: LocalTime,
) {

    fun getTotalPrice(): Int = eachPrice * count.toInt()
}
