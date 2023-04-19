package movie

@JvmInline
value class TicketQuantity(private val value: Int) {
    init {
        require(value > MIN_COUNT) { ERROR_COUNT }
    }

    fun toInt(): Int = value

    private fun willUnder(): Boolean = value - STEP <= MIN_COUNT

    operator fun inc(): TicketQuantity {
        return TicketQuantity(value + STEP)
    }
    operator fun dec(): TicketQuantity {
        return when {
            willUnder() -> this
            else -> TicketQuantity(value - STEP)
        }
    }

    override fun toString(): String {
        return value.toString()
    }

    companion object {
        private const val ERROR_COUNT = "티켓 수는 0보다 커야 합니다."
        private const val MIN_COUNT = 0
        private const val STEP = 1
    }
}
