package woowacourse.movie.model.ticketing

data class Ticket(
    val movieId: Long,
    val bookingDateTime: BookingDateTime,
    val bookingSeat: BookingSeat,
)
