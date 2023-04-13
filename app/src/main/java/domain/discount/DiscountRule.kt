package domain.discount

import domain.payment.PaymentAmount
import java.time.LocalDateTime

interface DiscountRule {
    fun getPaymentAmountResult(paymentAmount: PaymentAmount, screeningDateTime: LocalDateTime): PaymentAmount
}
