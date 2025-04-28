package woowacourse.movie.domain.model.ticket

import woowacourse.movie.domain.model.booking.Booking
import woowacourse.movie.domain.model.booking.PeopleCount
import woowacourse.movie.domain.model.seat.Seat
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalTime

data class Ticket(
    val title: String,
    val bookingDate: LocalDate,
    val bookingTime: LocalTime,
    val count: PeopleCount,
    val seats: Set<Seat>,
    val price: Int,
) : Serializable {
    companion object {
        fun initialize(
            booking: Booking,
            seats: Set<Seat>,
            price: Int,
        ): Ticket {
            return Ticket(
                title = booking.title,
                bookingDate = booking.bookingDate,
                bookingTime = booking.bookingTime,
                count = booking.count,
                seats = seats,
                price = price,
            )
        }
    }
}
