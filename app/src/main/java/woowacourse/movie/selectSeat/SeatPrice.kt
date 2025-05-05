package woowacourse.movie.selectSeat

import woowacourse.movie.model.Seat

enum class SeatPrice(
    val row: Char,
    val price: Int,
) {
    A('A', 10000),
    B('B', 10000),
    C('C', 15000),
    D('D', 15000),
    E('E', 12000),
    ;

    companion object {
        fun getPrice(seat: Seat): Int = entries.firstOrNull { it.row == seat.row }?.price ?: 0
    }
}
