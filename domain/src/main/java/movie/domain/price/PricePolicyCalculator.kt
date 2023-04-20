package movie.domain.price

import movie.domain.seat.Seat

class PricePolicyCalculator(private val discountPolicies: List<DiscountPolicy>) : PricePolicy {

    override fun totalPriceCalculate(selectedSeats: List<Seat>): Int {
        return selectedSeats.fold(0) { totalPrice, seat ->
            totalPrice + discountCalculate(seat.rank.price)
        }
    }

    override fun discountCalculate(price: Int): Int {
        return discountPolicies.fold(price) { discountedPrice, discountPolicy ->
            discountPolicy.discount(discountedPrice)
        }
    }
}
