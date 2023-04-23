package woowacourse.movie.domain

import java.time.LocalDateTime

class ReservationAgency(
    private val movie: Movie,
    private val peopleCount: Int,
    private val selectedDateTime: LocalDateTime
) {

    fun reserve(seats: List<Seat>): Reservation? = if (canReserve(seats)) {
        Reservation(
            movie, seats, selectedDateTime, calculateReservationFee(seats)
        )
    } else null

    fun canReserve(seats: List<Seat>): Boolean {
        if (seats.distinct().size != peopleCount) return false
        return true
    }

    fun calculateReservationFee(seats: List<Seat>): Money {
        val totalPrice = seats.fold(0) { sum, seat ->
            sum + seat.price
        }

        return DiscountPolicy.getDiscountedFee(selectedDateTime, Money(totalPrice), seats.size)
    }
}
