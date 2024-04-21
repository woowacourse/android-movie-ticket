package woowacourse.movie.model

class MovieReservationData {
    var ticketCount = MIN_TICKET_COUNT
        private set

    fun minusTicketCount() {
        require(isTicketCountValid()) { "티켓 개수가 1이면 내릴 수 없습니다." }
        ticketCount--
    }

    private fun isTicketCountValid() = ticketCount > MIN_TICKET_COUNT

    fun plusTicketCount() {
        ticketCount++
    }

    companion object {
        private const val MIN_TICKET_COUNT = 1
    }
}
