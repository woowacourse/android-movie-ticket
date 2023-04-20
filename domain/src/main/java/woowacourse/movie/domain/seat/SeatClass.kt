package woowacourse.movie.domain.seat

enum class SeatClass(val price: Int) {
    B(10_000),
    S(15_000),
    A(12_000),
    ;

    companion object {
        fun getClass(row: Int): SeatClass {
            return when (row) {
                in 1..2 -> B
                in 3..4 -> S
                5 -> A
                else -> throw IllegalArgumentException()
            }
        }
    }
}
