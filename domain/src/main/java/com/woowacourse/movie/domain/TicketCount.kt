package com.woowacourse.movie.domain

@JvmInline
value class TicketCount(val count: Int = MIN_TICKET_COUNT) {
    init {
        require(count >= MIN_TICKET_COUNT) { INVALID_TICKET_COUNT_EXCEPTION_MESSAGE }
    }

    operator fun dec(): TicketCount =
        TicketCount((count - TICKET_UP_DOWN_UNIT).coerceAtLeast(MIN_TICKET_COUNT))

    operator fun inc(): TicketCount = TicketCount(count + TICKET_UP_DOWN_UNIT)

    companion object {
        private const val MIN_TICKET_COUNT = 1
        private const val TICKET_UP_DOWN_UNIT = 1
        private const val INVALID_TICKET_COUNT_EXCEPTION_MESSAGE = "티켓 개수는 1개 이상이어야 합니다."
    }
}
