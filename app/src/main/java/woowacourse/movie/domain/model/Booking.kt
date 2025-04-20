package woowacourse.movie.domain.model

import java.io.Serializable

class Booking(
    val title: String,
    val bookingDate: String,
    val bookingTime: String,
    val count: PeopleCount,
    val ticketType: TicketType,
) : Serializable {
    val ticketPrice: Int get() = ticketType.price.times(count.value)
}
