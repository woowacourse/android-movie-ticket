package woowacourse.movie.domain.model.seat

typealias DomainSeat = Seat

data class Seat(
    val row: SeatRow,
    val col: SeatColumn,
) {
    fun getClass(): SeatClass = row.getClass()
}
