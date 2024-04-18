package woowacourse.movie.domain.model

class MovieReservationModel {
    var ticketCount = 1
        private set

    fun minusTicketCount() {
        if (ticketCount > 1) {
            ticketCount--
        }
    }

    fun plusTicketCount() {
        ticketCount++
    }
}
