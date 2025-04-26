package woowacourse.movie.domain.policy

enum class SeatType(
    val price: Int,
) {
    S(15000),
    A(12000),
    B(10000), ;

    companion object {
        fun fromSeatName(seatName: String): SeatType =
            when (seatName.firstOrNull()) {
                'C', 'D' -> S
                'E' -> A
                'A', 'B' -> B
                else -> throw IllegalArgumentException("존재하지 않는 좌석입니다.$seatName")
            }
    }
}
