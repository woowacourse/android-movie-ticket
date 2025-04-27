package woowacourse.movie.domain.model

enum class SeatGrade(
    val price: Int,
) {
    B(10_000),
    S(15_000),
    A(12_000),
    ;

    companion object {
        fun of(seat: Seat) =
            when (seat.row) {
                0, 1 -> B
                2, 3 -> S
                4 -> A
                else -> throw IllegalArgumentException("유효하지 않은 좌석입니다.")
            }
    }
}
