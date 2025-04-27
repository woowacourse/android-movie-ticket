package woowacourse.movie.model.ticket.seat

data class Seat(
    val row: SeatRow,
    val col: SeatCol,
) {
    val seatCode: String
        get() = "${row.rowSeatText}${col.colSeatText}"
}
