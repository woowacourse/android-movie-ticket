package woowacourse.movie.domain

enum class SeatGrade(
    val price: Int,
) {
    S(15_000),
    A(12_000),
    B(10_000),
    ;

    companion object {
        fun of(row: Int): SeatGrade =
            when (row) {
                3, 4 -> S
                5 -> A
                1, 2 -> B
                else -> B
            }
    }
}
