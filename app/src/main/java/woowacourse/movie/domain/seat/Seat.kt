package woowacourse.movie.domain.seat

data class Seat(val row: Int, val col: Int) {
    fun price() = SeatGrade.of(this).price
}
