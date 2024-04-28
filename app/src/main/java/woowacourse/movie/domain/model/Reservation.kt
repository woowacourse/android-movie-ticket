package woowacourse.movie.domain.model

import java.time.LocalDate
import java.time.LocalTime

data class Reservation(
    val id: Int,
    val screen: Screen,
    val ticket: Ticket,
) {
    val totalPrice: Int
        get() = ticket.count * screen.price
}

data class TimeReservation(
    val id: Int,
    val screen: Screen,
    val ticket: Ticket,
    val dateTime: DateTime,
){
    companion object {
        val NULL = TimeReservation(0, Screen.NULL, Ticket(1), DateTime(LocalDate.MIN, LocalTime.MIN))
    }
}

data class DateTime(
    val date: LocalDate,
    val time: LocalTime
)
