package woowacourse.movie.model

enum class SeatGrade(
    val price: Int,
) {
    S(15_000),
    A(12_000),
    B(10_000),
    ;

    companion object {
        fun calculateSeatGrade(row: SeatRow): SeatGrade =
            when (row.value) {
                1, 2 -> B
                3, 4 -> S
                5 -> A
                else -> throw IllegalArgumentException("알 수 없는 좌석 행입니다.")
            }
    }
}
