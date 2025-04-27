package woowacourse.movie.domain

enum class SeatRank(val price: Int) {
    S(15_000),
    A(12_000),
    B(10_000),
    ;

    companion object {
        fun get(row: Int) =
            when (row) {
                1, 2 -> S
                3, 4 -> B
                else -> A
            }
    }
}
