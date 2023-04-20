package movie.data

import movie.seat.Seat
import java.time.LocalDate
import java.time.LocalTime

class MovieTicket(
    val totalPrice: Int,
    val count: TicketCount,
    val title: String,
    val date: LocalDate,
    val time: LocalTime,
) {
    private val _seats: MutableList<Seat> = mutableListOf()
    val seats: List<Seat>
        get() = _seats.toList()

    fun addSeat(seat: Seat) {
        _seats.add(seat)
    }
}
