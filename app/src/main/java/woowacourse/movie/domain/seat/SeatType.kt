package woowacourse.movie.domain.seat

enum class SeatType(
    val price: Int,
) {
    S(15000),
    A(12000),
    B(10000), ;

    companion object {
        fun fromSeat(seat: Seat): SeatType =
            when (seat.row) {
                2, 3 -> S
                4 -> A
                0, 1 -> B
                else -> throw IllegalArgumentException("존재하지 않는 좌석입니다.")
            }
    }
}
