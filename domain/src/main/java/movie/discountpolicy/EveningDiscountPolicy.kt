package movie.discountpolicy

import java.time.LocalTime

class EveningDiscountPolicy(
    private val time: LocalTime,
) : DiscountPolicy {

    override fun getDiscountPrice(price: Int): Int {
        return when {
            isEvening() -> EVENING_DISCOUNT_AMOUNT
            else -> 0
        }
    }

    private fun isEvening(): Boolean {
        return time.hour > eveningTime
    }

    companion object {
        private const val EVENING_DISCOUNT_AMOUNT = 2000
        private const val eveningTime = 20
    }
}
