package woowacourse.movie.model

enum class Seat(val price: Int) {
    B(10_000),
    S(15_000),
    A(12_000);

    companion object {
        fun fromSeatId(seatId: String): Seat {
            return when (seatId.first()) {
                'A', 'B' -> B
                'C', 'D' -> S
                'E' -> A
                else -> throw IllegalArgumentException("알 수 없는 좌석 ID: $seatId")
            }
        }
    }
}
