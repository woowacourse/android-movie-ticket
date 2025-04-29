package woowacourse.movie.domain.seat

enum class SeatGrade(val price: Int) {
    S(15_000),
    A(12_000),
    B(10_000),
    ;

    companion object {
        fun of(seat: Seat) =
            when (seat.row) {
                0, 1 -> B
                2, 3 -> S
                else -> A
            }
    }
}
