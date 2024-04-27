package woowacourse.movie.domain.model

data class Reservation2(
    val id: Int,
    val screen: Screen,
    val ticket: Ticket,
    val seats: Seats,
) {
    init {
        require(ticket.count == seats.count()) { "예약된 좌석 수와 티켓 수가 일치하지 않습니다." }
    }
}
