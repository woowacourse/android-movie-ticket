package woowacourse.movie.domain.seat

enum class SeatGrade(val price: Long) {
    B(10000),
    A(12000),
    S(15000),
    ;

    companion object {
        fun getGrade(row: SeatRow): SeatGrade {
            return when (row) {
                SeatRow.A, SeatRow.B -> B
                SeatRow.C, SeatRow.D -> S
                else -> A
            }
        }
    }
}
