package woowacourse.movie.domain.seat

enum class SeatGrade {
    S,
    A,
    B,
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
