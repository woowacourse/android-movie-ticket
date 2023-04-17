package woowacourse.movie.domain.model.seat

typealias DomainSeat = Seat

class Seat(
    private val row: SeatRow,
    private val col: SeatColumn,
) {
    fun getClass(): SeatClass = row.getClass()
}
