package movie.domain

data class Count(val value: Int) {
    operator fun inc() = Count(value + 1)

    operator fun dec(): Count {
        val decCount = value - 1
        decCount.coerceAtLeast(MINIMUM_TICKET_COUNT)
        return Count(decCount)
    }

    override fun toString() = value.toString()

    companion object {
        private const val MINIMUM_TICKET_COUNT = 0
    }
}
