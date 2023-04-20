package com.woowacourse.movie.domain

@JvmInline
value class TicketCount(val count: Int = MIN_TICKET_COUNT) {
    init {
        require(count >= MIN_TICKET_COUNT) { INVALID_TICKET_COUNT_EXCEPTION_MESSAGE }
    }

    companion object {
        private const val MIN_TICKET_COUNT = 1
        private const val INVALID_TICKET_COUNT_EXCEPTION_MESSAGE = "티켓 개수는 최소 1장 이상이어야 합니다."
    }
}
