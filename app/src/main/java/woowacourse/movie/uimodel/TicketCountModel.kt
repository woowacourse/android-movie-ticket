package woowacourse.movie.uimodel

@JvmInline
value class TicketCountModel(val value: Int) {

    companion object {
        const val TICKET_COUNT_INTENT_KEY = "ticket_count"
        const val TICKET_COUNT_INSTANCE_KEY = "ticket_count"
    }
}
