package movie.domain

data class Count(val value: Int) {
    operator fun inc() = Count(value + 1)

    operator fun dec(): Count {
        var tmpCount = value - 1
        if (tmpCount <= MINIMUM_TICKET_COUNT) {
            tmpCount = MINIMUM_TICKET_COUNT
        }
        return Count(tmpCount)
    }

    override fun toString() = value.toString()

    companion object {
        private const val MINIMUM_TICKET_COUNT = 0
    }
}
