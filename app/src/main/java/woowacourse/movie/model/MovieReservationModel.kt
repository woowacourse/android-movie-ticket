package woowacourse.movie.model

class MovieReservationModel {
    var ticketCount = MIN_TICKET_COUNT
        private set

    fun minusTicketCount() {
        if (isTicketCountValid()) {
            ticketCount--
        }
    }

    private fun isTicketCountValid() = ticketCount > MIN_TICKET_COUNT

    fun plusTicketCount() {
        ticketCount++
    }

    companion object {
        private const val MIN_TICKET_COUNT = 1
    }
}
