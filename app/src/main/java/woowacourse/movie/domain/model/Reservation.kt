package woowacourse.movie.domain.model

data class Reservation(
    val id: Int,
    val screen: Screen,
    val ticket: Ticket,
    val seats: Seats,
    val dateTime: DateTime? = null,
) {
    init {
        require(ticket.count == seats.count()) { "예약된 좌석 수와 티켓 수가 일치하지 않습니다." }
    }

    companion object {
        val NULL =
            Reservation(
                id = -1,
                screen = Screen.NULL,
                ticket = Ticket(1),
                seats =
                    Seats(
                        Seat(Position(0, 0), Grade.S),
                    ),
            )
    }
}
