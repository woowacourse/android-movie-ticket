package woowacourse.movie.domain.policy

class PricingPolicy(
    private val selectedSeats: List<String>,
) {
    fun calculatePrice(): Int =
        selectedSeats.sumOf { seatName ->
            SeatType.fromSeatName(seatName).price
        }
}
