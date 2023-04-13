package domain.payment

@JvmInline
value class PaymentAmount(val value: Int) {
    init {
        require(value >= 0)
    }
}
