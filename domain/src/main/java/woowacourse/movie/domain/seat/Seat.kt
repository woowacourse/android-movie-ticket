package woowacourse.movie.domain.seat

data class Seat(
    val row: Int,
    val column: Int
) {
    fun getPriceByClass(): Int = SeatClass.getClass(row).price
}
