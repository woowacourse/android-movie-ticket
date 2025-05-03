package woowacourse.movie.domain.policy

import woowacourse.movie.domain.model.Seat

class PricingPolicy(
    private val selectedSeats: List<Seat>,
) {
    fun calculatePrice(): Int =
        selectedSeats.sumOf { seat ->
            SeatType.fromSeat(seat).price
        }
}
