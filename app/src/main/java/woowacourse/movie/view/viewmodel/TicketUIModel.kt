package woowacourse.movie.view.viewmodel

import SeatPosition
import woowacourse.movie.domain.Ticket
import java.io.Serializable
import java.time.LocalDateTime

fun Ticket.toUIModel() = TicketUIModel(
    price, date, numberOfPeople, seats
)

class TicketUIModel(
    val price: Int,
    val date: LocalDateTime,
    val numberOfPeople: Int,
    private val seats: List<SeatPosition>
) : Serializable {
    fun getSeats(): String {
        return seats.joinToString { "$it" }
    }
}
