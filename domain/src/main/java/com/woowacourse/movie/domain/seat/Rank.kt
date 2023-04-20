package com.woowacourse.movie.domain.seat

enum class Rank(val price: Int) {
    S(15_000),
    A(12_000),
    B(10_000);

    companion object {
        private const val B_RANK_MIN_RANGE = 0
        private const val B_RANK_MAX_RANGE = 1
        private const val A_RANK_MIN_RANGE = 2
        private const val A_RANK_MAX_RANGE = 3

        fun valueOf(seatPosition: SeatPosition): Rank =
            when (seatPosition.row.x) {
                in (B_RANK_MIN_RANGE..B_RANK_MAX_RANGE) -> B
                in (A_RANK_MIN_RANGE..A_RANK_MAX_RANGE) -> A
                else -> S
            }
    }
}
