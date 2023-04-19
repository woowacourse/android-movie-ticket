package domain.discount

import domain.payment.PaymentAmount
import java.time.LocalDateTime

interface DiscountEvent {
    fun discount(paymentAmount: PaymentAmount, screeningDateTime: LocalDateTime): PaymentAmount
}
