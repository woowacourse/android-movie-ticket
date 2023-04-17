package woowacourse.movie.domain

data class TicketCount(
    val numberOfPeople: Int = MIN_BOOKER_NUMBER,
) {
    fun increase(): TicketCount = TicketCount((numberOfPeople + 1).coerceAtMost(MAX_BOOKER_NUMBER))

    fun decrease(): TicketCount = TicketCount((numberOfPeople - 1).coerceAtLeast(MIN_BOOKER_NUMBER))

    companion object {
        private const val MIN_BOOKER_NUMBER = 1
        private const val MAX_BOOKER_NUMBER = 10
    }
}
