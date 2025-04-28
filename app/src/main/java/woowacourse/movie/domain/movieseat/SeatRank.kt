package woowacourse.movie.domain.movieseat

enum class SeatRank(val price: Int) {
    S(15_000),
    A(12_000),
    B(10_000),
    ;

    companion object {
        fun get(row: Int) =
            when (row) {
                0, 1 -> B
                2, 3 -> S
                else -> A
            }
    }
}
