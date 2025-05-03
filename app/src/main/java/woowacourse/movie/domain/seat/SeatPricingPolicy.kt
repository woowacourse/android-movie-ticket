package woowacourse.movie.domain.seat

class SeatPricingPolicy(
    private val selectedSeats: List<Seat>,
) {
    fun calculatePrice(): Int =
        selectedSeats.sumOf { seat ->
            SeatType.fromSeat(seat).price
        }
}
