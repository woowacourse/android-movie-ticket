package com.woowacourse.movie.domain.seat

enum class Rank(val price: Int) {
    S(15_000),
    A(12_000),
    B(10_000);

    companion object {
        fun valueOf(seatPosition: SeatPosition): Rank =
            SeatRankRule.getSeatRank(seatPosition)
    }
}
