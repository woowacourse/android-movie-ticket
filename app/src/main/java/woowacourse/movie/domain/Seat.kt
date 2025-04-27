package woowacourse.movie.domain

data class Seat(
    val position: Position,
) {
    fun seatPrice() = SeatRank.get(position.row).price
}
