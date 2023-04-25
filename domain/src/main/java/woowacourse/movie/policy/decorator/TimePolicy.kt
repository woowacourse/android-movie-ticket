package woowacourse.movie.policy.decorator

import woowacourse.movie.policy.Policy

class TimePolicy(policy: Policy) : PolicyDecorator(policy) {
    override fun cost(): Int {
        var payment = payment
        payment = earlyBirdDiscount(payment)
        payment = lateNightTimeDiscount(payment)
        return payment
    }

    private fun earlyBirdDiscount(price: Int): Int {
        if (ticket.bookedDateTime.hour in EARLY_TIMES) return (price - DISCOUNT_PRICE)
        return price
    }

    private fun lateNightTimeDiscount(price: Int): Int {
        if (ticket.bookedDateTime.hour in LATE_TIMES) return (price - DISCOUNT_PRICE)
        return price
    }

    companion object {
        private const val DISCOUNT_PRICE = 2000
        private val EARLY_TIMES = listOf(9, 10, 11)
        private val LATE_TIMES = listOf(20, 21, 22, 23)
    }
}
