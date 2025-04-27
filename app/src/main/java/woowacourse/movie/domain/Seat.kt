package woowacourse.movie.domain

data class Seat(val row: Int, val col: Int) {
    fun price() = SeatGrade.of(this).price
}
