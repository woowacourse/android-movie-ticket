package woowacourse.movie.model.ticket

data class Ticket(
    val movieId: Long,
    val bookingDateTime: BookingDateTime,
    val bookingSeat: BookingSeat,
)
