package woowacourse.movie.model

class MovieReservationData {
    var ticketCount = Count(MIN_TICKET_COUNT)
        private set

    fun minusTicketCount() {
        ticketCount--
    }

    fun plusTicketCount() {
        ticketCount++
    }

    companion object {
        private const val MIN_TICKET_COUNT = 1
    }
}
