package woowacourse.movie.domain.grade

import woowacourse.movie.domain.datetime.ScreeningDateTime
import woowacourse.movie.domain.price.TicketCount
import woowacourse.movie.domain.price.TicketPrice
import woowacourse.movie.domain.price.discount.runningpolicy.TimeMovieDayDiscountPolicy
import woowacourse.movie.domain.price.pricecalculate.PricePolicyCalculator

class SelectedSeats(
    val seats: MutableList<Position> = mutableListOf(),
    private val ticketCount: TicketCount,
    private val bookedScreeningDateTime: ScreeningDateTime
) {
    fun checkAddAvailability(): Boolean = seats.size < ticketCount.value

    fun checkFull(): Boolean = seats.size == ticketCount.value

    fun calculateTotalPrice(): TicketPrice {
        val priceCalculator =
            PricePolicyCalculator(TimeMovieDayDiscountPolicy(bookedScreeningDateTime).getDiscountPolicies())
        return applyDiscount(priceCalculator)
    }

    private fun applyDiscount(priceCalculator: PricePolicyCalculator) =
        seats.fold(TicketPrice(0)) { total, seat ->
            total + priceCalculator.discountCalculate(
                seat.getGradePrice()
            )
        }

    fun checkAlreadySelect(seat: Position): Boolean = seat in seats
}
