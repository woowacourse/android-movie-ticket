package movie.domain.price

import movie.domain.seat.Seat

interface PricePolicy {

    fun totalPriceCalculate(selectedSeats: List<Seat>): Int

    fun discountCalculate(price: Int): Int
}
