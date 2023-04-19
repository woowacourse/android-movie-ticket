package woowacourse.movie.policy.decorator

import woowacourse.movie.policy.Policy

class DatePolicy(policy: Policy) : PolicyDecorator(policy) {
    override fun cost(): Int {
        var payment = payment
        payment = movieDayDiscount(payment)
        return payment
    }

    private fun movieDayDiscount(price: Int): Int {
        if (ticket.bookedDateTime.dayOfMonth in MOVIE_DAYS) return (price * DISCOUNTED_PRICE_RATE).toInt()
        return price
    }

    companion object {
        private const val DISCOUNTED_PRICE_RATE = 0.9
        private val MOVIE_DAYS = listOf(10, 20, 30)
    }
}
