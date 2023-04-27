package domain.seatPolicy

import domain.Price

enum class SeatRank(val price: Price) {
    S(Price(15_000)),
    A(Price(12_000)),
    B(Price(10_000));

    companion object {
        fun getRank(row: Int): SeatRank {
            return when {
                (row in 1..2) -> B
                (row in 3..4) -> S
                else -> A
            }
        }
    }
}
