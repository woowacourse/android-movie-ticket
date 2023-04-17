package movie.discountpolicy

import java.time.LocalTime

class EarlyMorningDiscountPolicy(
    private val time: LocalTime,
) : DiscountPolicy {

    override fun getDiscountPrice(price: Int): Int {
        return when {
            isEarlyMorning() -> EARLY_MORNING_DISCOUNT_AMOUNT
            else -> 0
        }
    }

    private fun isEarlyMorning(): Boolean {
        return time.hour < earlyMorningTime
    }

    companion object {
        private const val EARLY_MORNING_DISCOUNT_AMOUNT = 2000
        private const val earlyMorningTime = 11
    }
}
