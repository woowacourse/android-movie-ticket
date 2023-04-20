package payment

@JvmInline
value class PaymentAmount(val value: Int) {
    init {
        require(value >= MINIMUM)
    }

    override fun toString(): String {
        return value.toString()
    }

    companion object {
        private const val MINIMUM = 0
    }
}
