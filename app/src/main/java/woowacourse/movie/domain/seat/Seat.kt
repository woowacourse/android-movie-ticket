package woowacourse.movie.domain.seat

data class Seat(val row: Int, val col: Int) {
    fun price() =
        when (SeatGrade.of(this)) {
            SeatGrade.S -> 15_000
            SeatGrade.A -> 12_000
            SeatGrade.B -> 10_000
        }
}
