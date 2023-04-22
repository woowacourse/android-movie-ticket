package com.woowacourse.domain.seat

enum class SeatTier(val price: Int) {
    B(10_000),
    A(12_000),
    S(15_000);

    companion object {
        fun getByRow(row: Int) = when (row + 1) {
            1, 2 -> B
            3, 4 -> S
            5 -> A
            else -> throw IllegalArgumentException("잘못된 값: $row 유효하지 않은 row 값입니다.")
        }
    }
}
