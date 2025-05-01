package woowacourse.movie.model.seat

enum class SeatGrade(
    val price: Int,
) {
    S(15_000),
    A(12_000),
    B(10_000),
    ;

    companion object {
        fun calculateSeatGrade(seatGridElement: SeatGridElement): SeatGrade =
            when (seatGridElement.value) {
                0, 1 -> B
                2, 3 -> S
                4 -> A
                else -> throw IllegalArgumentException("알 수 없는 좌석 행입니다.")
            }
    }
}
