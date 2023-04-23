package movie.domain.price

import movie.domain.seat.Seat

interface PricePolicy {

    fun totalPriceCalculate(selectedSeats: Set<Seat>): Int

    fun discountCalculate(price: Int): Int
}
