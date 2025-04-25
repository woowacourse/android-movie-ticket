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
                2, 3 -> S
                4 -> A
                0, 1 -> B
                else -> B
            }
    }
}
