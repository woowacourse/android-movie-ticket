package domain.seat

import domain.payment.PaymentAmount

enum class SeatRate {
    S {
        override fun getPaymentAmount() = PaymentAmount(S_RATE_PAYMENT_AMOUNT)
    },
    A {
        override fun getPaymentAmount() = PaymentAmount(A_RATE_PAYMENT_AMOUNT)
    },
    B {
        override fun getPaymentAmount() = PaymentAmount(B_RATE_PAYMENT_AMOUNT)
    };

    abstract fun getPaymentAmount(): PaymentAmount

    companion object {
        private const val B_RATE_PAYMENT_AMOUNT = 10_000
        private const val A_RATE_PAYMENT_AMOUNT = 12_000
        private const val S_RATE_PAYMENT_AMOUNT = 15_000
    }
}
