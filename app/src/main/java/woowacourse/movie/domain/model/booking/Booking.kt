package woowacourse.movie.domain.model.booking

import java.io.Serializable
import java.time.LocalDate
import java.time.LocalTime

class Booking(
    val title: String,
    val bookingDate: LocalDate,
    val bookingTime: LocalTime,
    val count: PeopleCount,
    val ticketType: TicketType,
) : Serializable {
    val ticketPrice: Int get() = ticketType.price * count.value

    constructor(
        title: String,
        bookingDate: String,
        bookingTime: String,
        peopleCount: PeopleCount,
        ticketType: TicketType,
    ) : this(
        title = title,
        bookingDate = LocalDate.parse(bookingDate),
        bookingTime = LocalTime.parse(bookingTime),
        count = peopleCount,
        ticketType = ticketType,
    )
}
