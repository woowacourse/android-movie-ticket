package movie.data

@JvmInline
value class TicketCount(private val value: Int) {
    init {
        require(value > MIN_COUNT) { ERROR_COUNT }
    }

    fun toInt(): Int = value

    fun isUp(other: Int): Boolean = value > other

    private fun willUnder(): Boolean = value - STEP <= MIN_COUNT

    operator fun inc(): TicketCount {
        return TicketCount(value + STEP)
    }
    operator fun dec(): TicketCount {
        return when {
            willUnder() -> this
            else -> TicketCount(value - STEP)
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
