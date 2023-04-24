package domain.reservation

@JvmInline
value class TicketCount(val value: Int) {

    init {
        require(value >= MINIMUM)
    }

    companion object {
        const val MINIMUM = 1
    }
}
