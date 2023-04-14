package domain.discount

import domain.payment.PaymentAmount
import java.time.LocalDateTime

class MovieDiscountEvent : DiscountEvent {

    override fun discount(
        paymentAmount: PaymentAmount,
        screeningDateTime: LocalDateTime
    ): PaymentAmount {
        val discounts: List<DiscountEvent> = listOf(
            MovieDayDiscountEvent(),
            EarlyNightDiscountEvent()
        )
        var resultPaymentAmount: PaymentAmount = paymentAmount

        discounts.forEach {
            resultPaymentAmount = it.discount(resultPaymentAmount, screeningDateTime)
        }

        return resultPaymentAmount
    }
}
