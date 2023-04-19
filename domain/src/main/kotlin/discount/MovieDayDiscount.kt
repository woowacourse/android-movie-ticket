package discount

import payment.PaymentAmount
import java.time.LocalDateTime

class MovieDayDiscount(
    private val percentage: Double = DEFAULT_MOVIE_DAY_PERCENTAGE,
    private val movieDays: List<Int> = DEFAULT_MOVIE_DAYS
) : DiscountRule {

    override fun getPaymentAmountResult(paymentAmount: PaymentAmount, screeningDateTime: LocalDateTime): PaymentAmount {
        if (!isDiscountCondition(screeningDateTime)) return paymentAmount
        return PaymentAmount((paymentAmount.value * percentage).toInt())
    }

    private fun isDiscountCondition(screeningDateTime: LocalDateTime): Boolean {
        if (screeningDateTime.dayOfMonth in movieDays) return true
        return false
    }

    companion object {
        private const val DEFAULT_MOVIE_DAY_PERCENTAGE: Double = 0.9
        private val DEFAULT_MOVIE_DAYS: List<Int> = listOf(10, 20, 30)
    }
}
