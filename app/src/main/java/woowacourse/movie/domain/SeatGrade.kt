package woowacourse.movie.domain

enum class SeatGrade(val price: Int) {
    S(15_000),
    A(12_000),
    B(10_000),
    ;

    companion object {
        fun of(seat: Seat) =
            when (seat.row) {
                1, 2 -> B
                3, 4 -> S
                else -> A
            }
    }
}
