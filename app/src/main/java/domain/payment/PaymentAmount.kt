package domain.payment

@JvmInline
value class PaymentAmount(val value: Int) {
    init {
        require(value >= MINIMUM)
    }

    companion object {
        private const val MINIMUM = 0
    }
}
