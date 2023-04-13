package movie

import java.io.Serializable
import java.time.LocalDate
import java.time.LocalTime

data class MovieTicket(
    val eachPrice: Int,
    val count: Int,
    val title: String,
    val date: LocalDate,
    val time: LocalTime,
) : Serializable {

    fun getTotalPrice(): Int = eachPrice * count
}
