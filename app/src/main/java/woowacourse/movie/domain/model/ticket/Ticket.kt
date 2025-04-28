package woowacourse.movie.domain.model.ticket

import woowacourse.movie.domain.model.booking.Booking
import woowacourse.movie.domain.model.booking.PeopleCount
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalTime

data class Ticket(
    val title: String,
    val bookingDate: LocalDate,
    val bookingTime: LocalTime,
    val count: PeopleCount,
    val seats: String,
    val price: Int,
) : Serializable {
    companion object {
        fun initialize(
            booking: Booking,
            price: Int,
        ): Ticket {
            return Ticket(
                title = booking.title,
                bookingDate = booking.bookingDate,
                bookingTime = booking.bookingTime,
                count = booking.count,
                seats = "",
                price = price,
            )
        }
    }
}
