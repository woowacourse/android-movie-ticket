package movie.domain

import movie.domain.datetime.ScreeningDateTime
import movie.domain.price.DiscountPolicy
import movie.domain.price.EarlyMorningLateNightDiscount
import movie.domain.price.MovieDayDiscount
import movie.domain.price.PricePolicyCalculator
import movie.domain.seat.Seat

class Ticket(
    val count: Count,
    val movieData: MovieData,
    val screeningDateTime: ScreeningDateTime,
    price: Int,
    val seatSelection: MutableSet<Seat> = mutableSetOf()
) {
    val price
        get() = calculatePrice()

    private fun calculatePrice(): Int {
        val discountPolicies = mutableListOf<DiscountPolicy>()
        if (screeningDateTime.checkMovieDay()) {
            discountPolicies.add(MovieDayDiscount())
        }
        if (screeningDateTime.checkEarlyMorningLateNight()) {
            discountPolicies.add(EarlyMorningLateNightDiscount())
        }
        return PricePolicyCalculator(discountPolicies).totalPriceCalculate(seatSelection)
    }
}
